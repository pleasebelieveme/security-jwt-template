package org.example.securityjwttemplate.domain.users.exception;

import org.example.securityjwttemplate.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ResponseCode {
	DUPLICATE_USER_EMAIL(HttpStatus.BAD_REQUEST, "USER-001", "중복된 이메일입니다."),
	NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER-002", "존재하지 않는 사용자입니다."),
	INVALID_REQUEST(HttpStatus.BAD_REQUEST, "USER-003", "유효하지 않은 요청입니다."),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER-004",  "유효하지 않은 비밀번호입니다."),
	NICKNAME_NOT_CHANGED(HttpStatus.BAD_REQUEST, "USER-005",  "현재 계정의 닉네임과 같습니다."),
	PASSWORD_NOT_CHANGED(HttpStatus.BAD_REQUEST, "USER-006",  "현재 계정의 비밀번호와 같습니다."),
	NO_UPDATE_TARGET(HttpStatus.BAD_REQUEST, "USER-007",  "닉네임이나 새 비밀번호 중 하나는 반드시 입력되어야 합니다."),
	DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "USER-008",  "이미 존재하는 닉네임입니다.");

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