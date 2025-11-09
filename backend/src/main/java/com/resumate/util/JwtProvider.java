package com.resumate.util;

import com.resumate.common.CommonApiException;
import com.resumate.common.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

	private final String secretKey;
	private final Key key;

	@Setter
	private long expirationMillis;

	@Setter
	private long refreshExpirationMillis;

	public JwtProvider(
			@Value("${jwt.secret.key}") String secretKey,
			@Value("${jwt.expiration.minute}") long expirationMinute,
			@Value("${jwt.expiration.refresh-minute}") long refreshMinute
	) {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(this.secretKey));
		this.expirationMillis = 1000L * 60 * expirationMinute;
		this.refreshExpirationMillis = 1000L * 60 * refreshMinute;
	}

	private String switchRoleCode(String roleCode) {
        return switch (roleCode) {
            case "ADMIN" -> "ADMIN";
            case "USER" -> "USER";
            default -> throw new IllegalArgumentException("Invalid role code: " + roleCode);
        };
    }

	public String createToken(int userId, String id, String authority) {

		Claims claims = Jwts.claims().setSubject(id);
		claims.put("userId", userId);
		claims.put("authority", switchRoleCode(authority));

		Date now = new Date();
		Date expiration = new Date(now.getTime() + expirationMillis);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public String createRefreshToken(int userId, String loginId, String authority) {

		Claims claims = Jwts.claims().setSubject(loginId);
		claims.put("userId", userId);
		claims.put("authority", switchRoleCode(authority));

		Date now = new Date();
		Date expiration = new Date(now.getTime() + refreshExpirationMillis);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractToken(String header) {

		if (header == null || !header.startsWith("Bearer ")) {
			return null;
		}
		return header.substring("Bearer ".length());
	}

	public Authentication getAuthentication(String token) {

		String loginId = getLoginId(token);
		String role = getAuthority(token);

		List<GrantedAuthority> auths = new ArrayList<>();
		if (role != null && !role.isBlank()) {
			String roleName = role.startsWith("ROLE_") ? role : "ROLE_" + role;
			auths.add(new SimpleGrantedAuthority(roleName));
		}

		UserDetails principal = org.springframework.security.core.userdetails.User
				.withUsername(loginId)
				.password("N/A")
				.authorities(auths)
				.build();

		return new UsernamePasswordAuthenticationToken(principal, null, auths);
	}

	public String getLoginId(String token) {
		return parseClaims(token).getSubject();
	}

	public String getUserId(HttpServletRequest request) {
		request.getHeader("Authorization");
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		} else {
			throw new CommonApiException(ErrorCode.INVALID_TOKEN);
		}
		return parseClaims(token).get("userId").toString();
	}

	public String getAuthority(String token) {

		if (token == null) {
			return null;
		}

		token = token.trim();
		if (token.startsWith("Bearer ")) {
			token = token.substring(7).trim();
		}

		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("authority", String.class);
	}

	public boolean validateExpiration(String token) {

		try {
			Date expiration = parseClaims(token).getExpiration();
			return !expiration.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public String createAccessTokenFromRefreshToken(String refreshToken) {

		Claims claims = parseClaims(refreshToken);
		Object userId = parseClaims(refreshToken).get("userId");
		return createToken((Integer)userId, claims.getSubject(), getAuthority(refreshToken));
	}

	public Claims parseClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public long getRefreshExpirationMinute() {
		return refreshExpirationMillis / (1000 * 60);
	}
}
