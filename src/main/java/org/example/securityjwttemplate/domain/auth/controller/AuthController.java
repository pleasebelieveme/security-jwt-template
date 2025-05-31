package org.example.securityjwttemplate.domain.auth.controller;


import org.example.securityjwttemplate.domain.auth.dto.request.LoginRequest;
import org.example.securityjwttemplate.domain.auth.dto.response.TokenResponse;
import org.example.securityjwttemplate.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
		TokenResponse tokenResponse = authService.login(request);
		return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
	}
}
