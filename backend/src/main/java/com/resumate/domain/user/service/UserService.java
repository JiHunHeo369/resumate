package com.resumate.domain.user.service;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resumate.domain.user.dto.UserDTO;
import com.resumate.util.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final JwtProvider jwtProvider;

	/**
	 * 로그인
	 */
	public UserDTO.LoginResponse login(UserDTO.LoginRequest request, HttpServletResponse response,
			HttpServletRequest httpRequest) {

		// UserRole userRole = userMapper.findByLoginId(request.getLoginId());

		// if (userRole == null) {
		// throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
		// }

		// User user = userRole.getUser();
		// String roleCode = userRole.getRoleCode();

		// try {
		// if (!satHash.scVerifyHashVal(request.getPassword(), user.getPassword())) {
		// throw new CommonApiException(ErrorCode.USER_INVALID_PASSWORD);
		// }
		// } catch (Exception e) {
		// throw new CommonApiException(ErrorCode.USER_INVALID_PASSWORD);
		// }

		// // AccessToken, RefreshToken 생성
		// String accessToken = jwtProvider.createToken(user.getId(), user.getLoginId(),
		// roleCode);
		// String refreshToken = jwtProvider.createRefreshToken(user.getId(),
		// user.getLoginId(), roleCode);

		// // TODO(배포 시 http 붙이고 secure true로 변경)
		// ResponseCookie refreshCookie = ResponseCookie.from("refreshToken",
		// refreshToken)
		// .httpOnly(true)
		// .secure(false)
		// .path("/")
		// .sameSite("Lax")
		// .maxAge((int) (jwtProvider.getRefreshExpirationMinute() * 60))
		// .build();
		// response.addHeader("Set-Cookie", refreshCookie.toString());

		// // AccessToken → 응답 헤더
		// response.setHeader("Authorization", "Bearer " + accessToken);

		// return UserDTO.LoginResponse.builder()
		// .name(user.getName())
		// .roleCode(roleCode)
		// .token(accessToken)
		// .build();
		return null;
	}

	/**
	 * 로그아웃
	 */
	public ResponseEntity<Void> logout(HttpServletResponse response) {

		// TODO(배포 시 secure true로 변경 - https 연결만 허용)
		ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", "")
				.httpOnly(true)
				.secure(false)
				.path("/")
				.sameSite("Lax")
				.maxAge(0)
				.build();

		response.addHeader("Set-Cookie", refreshCookie.toString());

		return ResponseEntity.ok().build();
	}

	/**
	 * 리프레쉬 토큰으로 AccessToken 재발급
	 */
	@Transactional
	public UserDTO.LoginResponse reissueAccessToken(HttpServletRequest request, HttpServletResponse response) {

		// String refreshToken = null;

		// if (request.getCookies() != null) {
		// for (Cookie cookie : request.getCookies()) {
		// if ("refreshToken".equals(cookie.getName())) {
		// refreshToken = cookie.getValue();
		// break;
		// }
		// }
		// }

		// if (refreshToken == null || !jwtProvider.validateExpiration(refreshToken)) {
		// throw new CommonApiException(ErrorCode.USER_INVALID_REFRESH_TOKEN);
		// }

		// try {
		// String newAccessToken =
		// jwtProvider.createAccessTokenFromRefreshToken(refreshToken);

		// response.setHeader("Authorization", "Bearer " + newAccessToken);
		// response.setHeader("Access-Control-Expose-Headers", "Authorization");

		// UserRole userRole =
		// userMapper.findByLoginId(jwtProvider.getLoginId(newAccessToken));

		// if (userRole == null) {
		// throw new CommonApiException(ErrorCode.USER_NOT_FOUND);
		// }

		// User user = userRole.getUser();
		// String roleCode = userRole.getRoleCode();

		// return UserDTO.LoginResponse.builder()
		// .name(user.getName())
		// .roleCode(roleCode)
		// .token(newAccessToken)
		// .build();

		// } catch (ExpiredJwtException e) {
		// throw new CommonApiException(ErrorCode.USER_INVALID_REFRESH_TOKEN);
		// }

		return null;
	}
}
