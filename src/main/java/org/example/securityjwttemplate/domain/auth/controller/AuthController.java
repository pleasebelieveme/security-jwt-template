package org.example.securityjwttemplate.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.securityjwttemplate.common.response.ApiResponse;
import org.example.securityjwttemplate.domain.auth.dto.request.LoginRequest;
import org.example.securityjwttemplate.domain.auth.dto.response.TokenResponse;
import org.example.securityjwttemplate.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
		TokenResponse tokenResponse = authService.login(request);
		return ResponseEntity.ok(ApiResponse.success("로그인 성공", tokenResponse));
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {
		authService.logout(request);
		return ResponseEntity.ok(ApiResponse.success("로그아웃 성공"));
	}

	@PostMapping("/reissue")
	public ResponseEntity<ApiResponse<TokenResponse>> reissue(@RequestHeader("Authorization") String refreshToken) {
		TokenResponse tokenResponse = authService.reissue(refreshToken);
		return ResponseEntity.ok(ApiResponse.success("토큰 재발급 성공", tokenResponse));
	}
}