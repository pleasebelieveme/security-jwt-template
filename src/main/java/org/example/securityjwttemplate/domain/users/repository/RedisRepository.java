package org.example.securityjwttemplate.domain.users.repository;

import org.example.securityjwttemplate.common.exception.BizException;
import org.example.securityjwttemplate.domain.users.exception.UserErrorCode;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
	//
	// private final RedisTemplate<String,String> redisTemplate;
	//
	// public String generateBlacklistKey(String token){
	// 	String blacklistKey = "blacklist:" + token;
	//
	// 	return blacklistKey;
	// }
	//
	// public boolean validateKey(String token){
	// 	try {
	// 		return redisTemplate.hasKey(generateBlacklistKey(token));
	// 	} catch (Exception e) {
	// 		throw new BizException(UserErrorCode.INVALID_REQUEST);
	// 	}
	// }

}
