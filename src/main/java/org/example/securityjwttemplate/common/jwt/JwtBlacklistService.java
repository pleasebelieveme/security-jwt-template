package org.example.securityjwttemplate.common.jwt;

import lombok.RequiredArgsConstructor;
import org.example.securityjwttemplate.domain.users.repository.RedisRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final RedisRepository redisRepository;

    /**
     * 토큰이 블랙리스트에 존재하는지 확인
     */
    public boolean isBlacklisted(String token) {
        return redisRepository.validateKey(token);
    }

    /**
     * 블랙리스트에 토큰을 등록
     * @param token 블랙리스트로 처리할 토큰
     * @param expirationMillis 만료 시간(ms)
     */
    public void addToBlacklist(String token, long expirationMillis) {
        redisRepository.saveBlackListToken(token, expirationMillis);
    }
}
