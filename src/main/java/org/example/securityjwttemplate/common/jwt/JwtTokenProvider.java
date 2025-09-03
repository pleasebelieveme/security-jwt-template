package org.example.securityjwttemplate.common.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.securityjwttemplate.domain.users.entity.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static org.example.securityjwttemplate.common.jwt.JwtConstants.*;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiration;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 액세스 토큰 생성
	 */
	public String createAccessToken(Long userId, UserRole role) {
		return buildToken(userId, role, accessTokenExpiration, false);
	}

	/**
	 * 리프레시 토큰 생성 (JTI 포함)
	 */
	public String createRefreshToken(Long userId, UserRole role) {
		return buildToken(userId, role, refreshTokenExpiration, true);
	}

	/**
	 * 토큰 유효성 검사
	 */
	public boolean validateToken(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.warn("JWT 만료됨");
		} catch (JwtException | IllegalArgumentException e) {
			log.warn("유효하지 않은 JWT: {}", e.getMessage());
		}
		return false;
	}

	/**
	 * 토큰에서 사용자 정보 추출
	 */
	public UserAuth getUserAuth(String token) {
		Claims claims = parseClaims(token);
		Long userId = Long.parseLong(claims.getSubject());
		UserRole userRole = UserRole.valueOf(claims.get(CLAIM_USER_ROLE, String.class));
		return new UserAuth(userId, userRole);
	}

	/**
	 * 토큰에서 JTI 추출
	 */
	public String getJti(String token) {
		return parseClaims(token).get(CLAIM_JTI, String.class);
	}

	/**
	 * 남은 만료 시간 조회
	 */
	public long getExpiration(String token) {
		return parseClaims(token).getExpiration().getTime() - System.currentTimeMillis();
	}

	// 내부 공통 메서드
	private String buildToken(Long userId, UserRole role, long expiration, boolean includeJti) {
		JwtBuilder builder = Jwts.builder()
				.setSubject(String.valueOf(userId))
				.claim(CLAIM_USER_ROLE, role.name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration));

		if (includeJti) {
			builder.claim(CLAIM_JTI, UUID.randomUUID().toString());
		}

		return builder.signWith(getSigningKey()).compact();
	}

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
