package org.example.securityjwttemplate.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(

	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	String email,

	@NotBlank(message = "현재 비밀번호를 입력해주세요.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
		message = "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 모두 포함해야 합니다"
	)
	String password

) {
}