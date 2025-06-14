package org.example.securityjwttemplate.domain.auth.service.unit;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import jakarta.servlet.http.HttpServletRequest;
import org.example.securityjwttemplate.common.jwt.JwtUtil;
import org.example.securityjwttemplate.domain.auth.service.AuthService;
import org.example.securityjwttemplate.domain.users.repository.RedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AuthLogTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private RedisRepository redisRepository;

    @InjectMocks
    private AuthService authService;

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // 로그 캡처 준비 (AuthService 클래스 로거)
        Logger logger = (Logger) LoggerFactory.getLogger(AuthService.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    void 로그아웃_중_예외_발생시_로그가_기록된다() {
        // given
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        String fakeToken = "test-token";

        when(jwtUtil.extractToken(request)).thenReturn(fakeToken);
        when(jwtUtil.validateToken(fakeToken)).thenReturn(true);

        // jwtUtil.extractUserAuth 호출 시 예외 던지기
        Mockito.doThrow(new RuntimeException("테스트용 예외")).when(jwtUtil).extractUserAuth(fakeToken);

        // when
        authService.logout(request);

        // then
        List<ILoggingEvent> logsList = listAppender.list;
        boolean hasWarnLog = logsList.stream()
                .anyMatch(log -> log.getLevel().toString().equals("WARN")
                        && log.getFormattedMessage().contains("로그아웃 처리 중 예외 발생"));

        assertThat(hasWarnLog).isTrue();
    }
}
