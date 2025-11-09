package com.resumate.filter;

import com.resumate.util.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain) throws ServletException, IOException {
		// 로그인 및 회원가입시 예외처리
		List<String> excludedPaths = List.of(
				"/api/users/login", "/api/test/");

		// 실제 요청 경로
		String path = request.getServletPath();

		if (excludedPaths.stream().anyMatch(path::equals)) {
			filterChain.doFilter(request, response);
			return;
		}

		String header = request.getHeader("Authorization");
		String accessToken = jwtProvider.extractToken(header);

		try {
			if (accessToken != null && jwtProvider.validateExpiration(accessToken)) {
				// Access Token 유효할 때 인증 설정
				Authentication auth = jwtProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(auth);

				log.info("JWT 인증 성공: 사용자ID = {}, 권한 = {}", auth.getName(), auth.getAuthorities());

			} else {
				// Access Token이 만료되었거나 없는 경우 Refresh Token 검사
				String refreshToken = extractRefreshTokenFromCookie(request);

				if (refreshToken != null && jwtProvider.validateExpiration(refreshToken)) {
					// Refresh Token 유효하면 새로운 Access Token 발급
					String newAccessToken = jwtProvider.createAccessTokenFromRefreshToken(refreshToken);

					if (newAccessToken != null) {
						// 새 Access Token으로 인증 설정
						Authentication auth = jwtProvider.getAuthentication(newAccessToken);
						SecurityContextHolder.getContext().setAuthentication(auth);

						// 새 Access Token을 응답 헤더에 추가 (Bearer 스킴 포함)
						response.setHeader("Authorization", "Bearer " + newAccessToken);

					} else {
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
				} else {
					// Refresh Token도 없거나 만료됨
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
			}
		} catch (ExpiredJwtException e) {
			// Access Token 만료
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} catch (JwtException | IllegalArgumentException e) {
			// JWT 검증 실패
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private String extractRefreshTokenFromCookie(HttpServletRequest request) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("refreshToken".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
