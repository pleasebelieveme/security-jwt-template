package org.example.securityjwttemplate.domain.users.controller;

import org.example.securityjwttemplate.common.jwt.UserAuth;
import org.example.securityjwttemplate.common.response.ApiResponse;
import org.example.securityjwttemplate.domain.users.dto.request.UserCreateRequest;
import org.example.securityjwttemplate.domain.users.dto.request.UserUpdateRequest;
import org.example.securityjwttemplate.domain.users.dto.response.UserResponse;
import org.example.securityjwttemplate.domain.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserCreateRequest request) {
		userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("회원가입이 완료되었습니다."));
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<UserResponse>> findById(@AuthenticationPrincipal UserAuth userAuth) {
		UserResponse response = userService.findById(userAuth);
		return ResponseEntity.ok(ApiResponse.success("회원 조회 성공", response));
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<Void>> updateUser(@Valid @RequestBody UserUpdateRequest request,
														@AuthenticationPrincipal UserAuth userAuth) {
		userService.updateUser(request, userAuth);
		return ResponseEntity.ok(ApiResponse.success("회원 정보 수정 완료"));
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal UserAuth userAuth) {
		userService.deleteUser(userAuth);
		return ResponseEntity.ok(ApiResponse.success("회원 탈퇴 완료"));
	}
}


