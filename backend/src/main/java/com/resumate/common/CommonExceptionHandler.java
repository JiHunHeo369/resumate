package com.boilerplate.common;

import com.boilerplate.common.dto.ErrorResponseDTO;
import com.boilerplate.util.HttpRequestUtils;
import com.boilerplate.util.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonExceptionHandler {

	// private final LogMapper logMapper;
	// private final JwtProvider jwtProvider;

	// public CommonExceptionHandler(LogMapper logMapper, JwtProvider jwtProvider) {
	// this.logMapper = logMapper;
	// this.jwtProvider = jwtProvider;
	// }

	private ResponseEntity<ErrorResponseDTO> buildErrorResponse(ErrorCode errorCode, String message) {
		ErrorResponseDTO response = ErrorResponseDTO.builder()
				.status(errorCode.getStatus().value())
				.message(message != null ? message : errorCode.getMessage())
				.code(errorCode.getStatus().name())
				.build();
		return ResponseEntity.status(errorCode.getStatus()).body(response);
	}

	@ExceptionHandler(CommonApiException.class)
	public ResponseEntity<ErrorResponseDTO> handleCommonApiException(CommonApiException ex) {
		ErrorResponseDTO response = ErrorResponseDTO.builder()
				.message(ex.getMessage())
				.status(ex.getHttpStatus().value())
				.code(ex.getHttpStatus().name())
				.build();
		return ResponseEntity.status(ex.getHttpStatus()).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, HttpServletRequest request) {
		ErrorCode errorCode;
		String message = null;

		if (ex instanceof BindException bindEx) {
			errorCode = ErrorCode.COMMON_INVALID_REQUEST_BODY;
			message = bindEx.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(";"));
		} else if (ex instanceof MethodArgumentNotValidException validEx) {
			errorCode = ErrorCode.COMMON_INVALID_REQUEST_BODY;
			message = validEx.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(";"));
		} else if (ex instanceof AccessDeniedException) {
			errorCode = ErrorCode.COMMON_ACCESS_DENIED;
		} else if (ex instanceof HttpRequestMethodNotSupportedException || ex instanceof NoHandlerFoundException) {
			errorCode = ErrorCode.COMMON_RESOURCE_NOT_FOUND;
		} else if (ex instanceof ExpiredJwtException) {
			errorCode = ErrorCode.EXPIRED_TOKEN;
		} else {
			ex.printStackTrace();
			errorCode = ErrorCode.COMMON_INTERNAL_SERVER_ERROR;
			message = "예상치 못한 오류가 발생했습니다.";
		}

		int httpStatus = errorCode.getStatus().value();

		// persistErrorLog(ex, httpStatus, request);

		return buildErrorResponse(errorCode, message);
	}

	// private void persistErrorLog(Exception ex, int httpStatus, HttpServletRequest
	// request) {
	// try {
	// ErrorLog log = new ErrorLog();

	// // 예외 관련
	// log.setExceptionClass(ex.getClass().getName());
	// log.setMessage(truncate(ex.getMessage(), 2000));
	// Throwable rootCause = findRootCause(ex);
	// log.setRootCause(rootCause != null ? rootCause.getClass().getName() : null);

	// // HTTP / request 관련
	// log.setHttpStatus(httpStatus);
	// if (request != null) {
	// log.setHttpMethod(request.getMethod());
	// log.setPath(request.getRequestURI());
	// log.setClientIp(HttpRequestUtils.extractClientIp(request));
	// log.setUserAgent(truncate(request.getHeader("User-Agent"), 512));

	// String userId = jwtProvider.getUserId(request);
	// if (userId != null) {
	// log.setUserId(Integer.parseInt(userId));
	// } else {
	// log.setUserId(null);
	// }
	// }

	// logMapper.insertErrorLog(log);
	// } catch (Exception loggingEx) {
	// loggingEx.printStackTrace();
	// }
	// }

	private static String truncate(String s, int max) {
		if (s == null)
			return null;
		return s.length() <= max ? s : s.substring(0, max);
	}

	private static Throwable findRootCause(Throwable t) {
		Throwable root = t;
		while (root.getCause() != null && root.getCause() != root) {
			root = root.getCause();
		}
		return root;
	}

}