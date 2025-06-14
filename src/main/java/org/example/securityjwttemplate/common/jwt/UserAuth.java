package org.example.securityjwttemplate.common.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.securityjwttemplate.domain.users.entity.UserRole;

@Getter
@RequiredArgsConstructor
public class UserAuth {
	private final Long id;
	private final UserRole userRole;
}