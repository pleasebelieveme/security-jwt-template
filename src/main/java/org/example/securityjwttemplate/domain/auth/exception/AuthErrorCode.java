package org.example.securityjwttemplate.domain.auth.exception;

import org.example.securityjwttemplate.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
	NOT_FOUND_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "A001", "액세스 토큰이 유효한 형태가 아닙니다."),
	NOT_FOUND_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "리프레시 토큰이 유효한 형태가 아닙니다.");


	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public HttpStatus getStatus() {
		return httpStatus;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
