package org.example.securityjwttemplate.common.exception;

public interface ErrorCode {
	int getStatus();

	String getCode();

	String getMessage();
}
