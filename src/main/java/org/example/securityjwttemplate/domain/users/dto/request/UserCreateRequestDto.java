package org.example.securityjwttemplate.domain.users.dto.request;

import org.example.securityjwttemplate.domain.users.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCreateRequestDto {

	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private final String email;

	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
		message = "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 모두 포함해야 합니다"
	)
	private final String password;

	@NotBlank(message = "이름은 필수 입력값입니다.")
	@Size(min = 2, max = 20, message = "이름 최대 20글자가 넘지 않도록 해주십시오.")
	private final String name;

	@NotBlank(message = "닉네임은 필수 입력값입니다.")
	@Size(min = 2, max = 30, message = "이름 최대 30글자가 넘지 않도록 해주십시오.")
	private final String nickname;

	private final UserRole userRole;

}
