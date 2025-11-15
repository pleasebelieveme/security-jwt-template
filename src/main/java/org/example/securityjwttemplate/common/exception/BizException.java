package org.example.securityjwttemplate.common.exception;

import org.example.securityjwttemplate.common.code.ResponseCode;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

	private final ResponseCode responseCode;

	public BizException(ResponseCode responseCode) {
		super(responseCode.getMessage());
		this.responseCode = responseCode;
	}
}