package org.example.securityjwttemplate.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "입력값이 올바르지 않습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

    @Override
    public int getStatus() {
        return status.value();
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
