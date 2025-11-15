package org.example.securityjwttemplate.domain.users.controller;

import org.example.securityjwttemplate.common.code.CommonSuccessCode;
import org.example.securityjwttemplate.common.jwt.UserAuth;
import org.example.securityjwttemplate.common.response.ApiResponse;
import org.example.securityjwttemplate.domain.users.dto.request.UserCreateRequest;
import org.example.securityjwttemplate.domain.users.dto.request.UserUpdateRequest;
import org.example.securityjwttemplate.domain.users.dto.response.UserResponse;
import org.example.securityjwttemplate.domain.users.exception.UserSuccessCode;
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
@RequestMapping("/api/v3/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserCreateRequest request) {
		userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(UserSuccessCode.CREATED));
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<UserResponse>> findById(@AuthenticationPrincipal UserAuth userAuth) {
		UserResponse response = userService.findById(userAuth);
		return ResponseEntity.ok(ApiResponse.success(UserSuccessCode.OK, response));
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<Void>> updateUser(@Valid @RequestBody UserUpdateRequest request,
														@AuthenticationPrincipal UserAuth userAuth) {
		userService.updateUser(request, userAuth);
		return ResponseEntity.ok(ApiResponse.success(UserSuccessCode.OK));
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse<Void>> deleteUser(@AuthenticationPrincipal UserAuth userAuth) {
		userService.deleteUser(userAuth);
		return ResponseEntity.ok(ApiResponse.success(CommonSuccessCode.OK));
	}
}


