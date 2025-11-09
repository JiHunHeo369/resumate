package com.resumate.domain.user.controller;

import com.resumate.domain.user.dto.UserDTO;
import com.resumate.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "사용자 관련 기능")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserDTO.LoginResponse> login(@Validated @RequestBody UserDTO.LoginRequest request,
																	HttpServletResponse response, HttpServletRequest httpRequest) {
		return ResponseEntity.ok(userService.login(request,response,httpRequest));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpServletResponse response) {
		return userService.logout(response);
	}

	@PostMapping("/reissue")
	public ResponseEntity<UserDTO.LoginResponse> reissueAccessToken(HttpServletRequest request,
																	HttpServletResponse response) {
		return ResponseEntity.ok(userService.reissueAccessToken(request,response));
	}
}
