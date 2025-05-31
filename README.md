### ✅ Spring Boot JWT 인증 템플릿 (Java 17 기준)

---

## 📦 Java & Spring 환경
- **Java 버전:** 17
- **Spring Boot:** 3.2.x
- **Gradle:** 8.2+
- **의존성:** Spring Security, JWT

---

## 🗂️ 패키지 구조 예시

```
org.example.hansabal
├── common
│   └── jwt
│       ├── JwtUtil.java
│       ├── JwtFilter.java
│       └── UserAuth.java
├── config
│   └── SecurityConfig.java
├── domain
│   └── users
│       ├── entity
│       │   └── UserRole.java
│       ├── repository
│       │   └── RedisRepository.java
├── global
│   └── annotation
│       └── LoginUser.java (custom @AuthenticationPrincipal)
```

---

## 🔐 JwtFilter.java
```java
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(...) {
        String token = jwtUtil.extractToken(request);
        if (redisRepository.validateKey(token)) {
            response.sendError(...);
            return;
        }
        if (jwtUtil.validateToken(token)) {
            UserAuth userAuth = jwtUtil.extractUserAuth(token);
            List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + userAuth.getUserRole().name())
            );
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userAuth, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
```

---

## 🔧 JwtUtil.java
```java
public class JwtUtil {
    public String createToken(Long id, UserRole userRole) {...}
    public UserAuth extractUserAuth(String token) {...}
    public boolean validateToken(String token) {...}
    public String extractToken(HttpServletRequest request) {...}
    public long getExpiration(String token) {...}
}
```

---

## 👤 UserAuth.java
```java
@Getter
@RequiredArgsConstructor
public class UserAuth {
    private final Long id;
    private final UserRole userRole;
}
```

---

## 🛡️ SecurityConfig.java
```java
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ...
        return http.build();
    }
}
```

---

## 📘 README 템플릿 일부
```md
### 🔐 인증 흐름
1. 클라이언트 로그인 요청 (ID/PW)
2. 서버에서 JWT 생성 → 응답 헤더에 반환
3. 클라이언트가 이후 모든 요청에 JWT 포함
4. JwtFilter에서 토큰 추출 + 검증 + 시큐리티 컨텍스트 저장
```

### 🧑‍💻 주입 예시
```java
@GetMapping("/me")
public String getMyInfo(@LoginUser UserAuth userAuth) {
    return "Hello, user " + userAuth.getId() + " with role: " + userAuth.getUserRole();
}
```
