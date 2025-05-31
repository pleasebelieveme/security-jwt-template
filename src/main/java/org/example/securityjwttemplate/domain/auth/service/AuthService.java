package org.example.securityjwttemplate.domain.auth.service;

import org.example.securityjwttemplate.common.exception.BizException;
import org.example.securityjwttemplate.common.security.jwt.JwtUtil;
import org.example.securityjwttemplate.domain.auth.dto.request.LoginRequest;
import org.example.securityjwttemplate.domain.auth.dto.response.TokenResponse;
import org.example.securityjwttemplate.domain.users.entity.User;
import org.example.securityjwttemplate.domain.users.exception.UserErrorCode;
import org.example.securityjwttemplate.domain.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // AuthService의 모든 public 메서드에만 트랜잭션 적용
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public TokenResponse login(LoginRequest request) {
		User user = userRepository.findByEmailOrElseThrow(request.email());

		if (!passwordEncoder.matches(request.password(), user.getPassword())) {
			throw new BizException(UserErrorCode.INVALID_PASSWORD);
		}

		String accessToken = jwtUtil.createToken(user.getId(), user.getUserRole());
		return new TokenResponse(accessToken);
	}
}