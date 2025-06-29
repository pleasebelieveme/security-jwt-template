package org.example.securityjwttemplate.domain.users.controller;

import org.example.securityjwttemplate.common.jwt.UserAuth;
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
	public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateRequest request) {
		userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/me")
	public ResponseEntity<UserResponse> findById(@AuthenticationPrincipal UserAuth userAuth) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userAuth));
	}

	@PatchMapping
	public ResponseEntity<Void> updateUser(@Valid @RequestBody UserUpdateRequest request, UserAuth userAuth) {
		userService.updateUser(request, userAuth);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserAuth userAuth) {
		userService.deleteUser(userAuth);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

