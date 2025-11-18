package com.resumate.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDTO {

	@NoArgsConstructor
	@Getter
	@Setter
	public static class LoginRequest {

		@NotBlank(message = "아이디는 공백일 수 없습니다.")
		@Size(max = 20, message = "아이디는 20자 이내입니다.")
		private String loginId;

		@NotBlank(message = "비밀번호는 공백일 수 없습니다.")
		@Size(max = 30)
		private String password;
	}

	@Getter
	@Builder
	public static class LoginResponse {
		private String name;
		private String roleCode;
		private String token;
	}
}
