package com.boilerplate.domain.user.entity;

import com.boilerplate.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

	@Schema(description = "사용자 아이디")
	private Integer id;

	@Schema(description = "로그인 아이디")
	private String loginId;

	@Schema(description = "비밀번호")
	private String password;

	@Schema(description = "이름")
	private String name;

	@Schema(description = "이메일")
	private String email;

	@Schema(description = "삭제여부")
	private Boolean deleted;

	@Schema(description = "권한")
	private Integer roleId;
}