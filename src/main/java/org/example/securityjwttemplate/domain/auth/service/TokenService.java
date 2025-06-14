package org.example.securityjwttemplate.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.securityjwttemplate.common.jwt.JwtUtil;
import org.example.securityjwttemplate.domain.auth.dto.response.TokenResponse;
import org.example.securityjwttemplate.domain.users.entity.UserRole;
import org.example.securityjwttemplate.domain.users.repository.RedisRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtUtil jwtUtil;
	private final RedisRepository redisRepository;

	public TokenResponse generateTokens(Long userId, UserRole userRole) {
		String accessToken = jwtUtil.createToken(userId, userRole);
		String refreshToken = jwtUtil.createRefreshToken(userId, userRole);
		return new TokenResponse(accessToken, refreshToken);
	}

	public void saveRefreshToken(Long userId, String refreshToken) {
		long refreshExpiration = jwtUtil.getRefreshExpiration(refreshToken);
		redisRepository.saveRefreshToken(userId, refreshToken, refreshExpiration);
	}
}
