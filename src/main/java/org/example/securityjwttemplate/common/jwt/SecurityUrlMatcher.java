package org.example.securityjwttemplate.common.jwt;

import java.util.Arrays;

public class SecurityUrlMatcher {
	public static final String[] PUBLIC_URLS = {
		"/api/v3/users",
		"/api/v3/auth/login",
	};

	public static final String[] ADMIN_URLS = {
		"/api/admin/**"
	};

	public static final String REFRESH_URL = "/api/auth/reissue";

	public static boolean isRefreshUrl(String path) {
		return REFRESH_URL.equals(path);
	}

	public static boolean isPublicUrl(String path) {
		return Arrays.stream(PUBLIC_URLS).anyMatch(path::startsWith);
	}
}
