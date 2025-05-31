package org.example.securityjwttemplate.common.security.jwt;

import org.example.securityjwttemplate.domain.users.entity.UserRole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAuth {
	private final Long id;
	private final UserRole userRole;
}