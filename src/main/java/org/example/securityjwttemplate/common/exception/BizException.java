package org.example.securityjwttemplate.common.exception;

import org.example.securityjwttemplate.common.code.ResponseCode;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

	private final ResponseCode responseCode;

	public BizException(String message, ResponseCode responseCode) {
		super(message);
		this.responseCode = responseCode;
	}

	public BizException(ResponseCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}

	/* 3. getStatus(), getCode(), getErrorMessage() 메서드는 제거됨 */
	// GlobalExceptionHandler에서 exception.getResponseCode().getStatus() 등으로 접근 가능합니다.
}