package com.boilerplate.common;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

	// Common
	COMMON_INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다."),
	COMMON_METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "유효하지 않은 HTTP 메소드입니다."),
	COMMON_LOGIN_SESSION_EXPIRED(HttpStatus.FORBIDDEN, "로그인 세션이 만료되었습니다."),
	COMMON_PRINCIPAL_NOT_FOUND(HttpStatus.UNAUTHORIZED, "인증 정보를 찾을 수 없습니다."),
	COMMON_ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
	COMMON_RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
	COMMON_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
	NO_INFO_FORUND(HttpStatus.INTERNAL_SERVER_ERROR, "정보를 찾을 수 없습니다."),
	FILE_NOT_ALLOWED_TYPE(HttpStatus.BAD_REQUEST, "허용되지 않은 확장자 입니다."),
	NOT_FOUND_DATA(HttpStatus.BAD_REQUEST, "존재하지 않는 정보입니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 엑세스 정보입니다."),
	COMMON_FAIL_MAIL_SEND(HttpStatus.INTERNAL_SERVER_ERROR, "만료된 엑세스 정보입니다."),

	// User
	INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않는 토큰입니다."),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
	USER_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
	NOT_MATCHED_CURRENT_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호가 일치하지 않습니다."),
	NEW_PASSWORD_SAME_AS_CURRENT(HttpStatus.BAD_REQUEST, "새 비밀번호가 현재 비밀번호와 같습니다."), // ← 추가
	USER_INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "쿠키에 리프레쉬 토큰이 존재하지 않습니다."),
	USER_EMAIL_ALREADY_USED(HttpStatus.BAD_REQUEST, "사용중인 이메일 입니다."),
	LOGIN_ID_ALREADY_USED(HttpStatus.BAD_REQUEST, "사용중인 아이디 입니다."),
	USER_EXCEEDED_MAX_LOGIN_ATTEMPTS(HttpStatus.BAD_REQUEST, "로그인 시도 횟수가 5회를 초과했습니다. 관리자에게 문의하세요."),
	USER_SUB_LOGIN_KEY_ALREADY_USED(HttpStatus.BAD_REQUEST, "사용중인 로그인 키 입니다."),
	USER_DUPLICATE(HttpStatus.BAD_REQUEST, "중복된 유저입니다."),
	NOT_RESOURCE_OWNER(HttpStatus.FORBIDDEN, "본인이 작성한 글이 아닙니다.");


	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

}
