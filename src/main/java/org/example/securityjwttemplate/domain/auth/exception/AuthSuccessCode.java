package org.example.securityjwttemplate.domain.auth.exception;

import org.example.securityjwttemplate.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements ResponseCode {
	// [HTTP Status, 커스텀 코드, 기본 메시지]
	OK(HttpStatus.OK, "AUTH-200", "요청이 성공적으로 처리되었습니다."),
	CREATED(HttpStatus.CREATED, "AUTH-201", "자원이 성공적으로 생성되었습니다."),
	NO_CONTENT(HttpStatus.NO_CONTENT, "AUTH-204", "요청은 성공했지만 응답 본문이 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

}
