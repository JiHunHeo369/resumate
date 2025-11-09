package com.resumate.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumate.common.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain)
		throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException ex) {
			setErrorResponse(response, ErrorCode.COMMON_LOGIN_SESSION_EXPIRED);
		}
	}

	public void setErrorResponse(HttpServletResponse response, ErrorCode errorCode)
		throws IOException {
		response.setStatus(errorCode.getStatus().value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonStr = new ObjectMapper().writeValueAsString(errorCode.getMessage());

		response.getWriter().write(jsonStr);
	}
}
