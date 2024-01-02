package com.example.project.handle;

import com.example.project.config.TokenProvider;
import com.example.project.dto.AddUserRequest;
import com.example.project.entity.RefreshToken;
import com.example.project.entity.UserEntity;
import com.example.project.repository.RefreshTokenRepository;
import com.example.project.service.UserService;
import com.example.project.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class LoginHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    public static final String REDIRECT_PATH = "/";

    public void login(@ModelAttribute AddUserRequest req, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserEntity user = userService.login(req);


        String newRefreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);

        System.out.println(newRefreshToken + "리프레시 토큰 생성");
        saveRefreshToken(user.getId(), newRefreshToken);
        addRefreshTokenToCookie(request, response, newRefreshToken);

        // 엑세스 토큰 생성 -> 패스에 토큰 추가
        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
        System.out.println(accessToken + "엑세스 토큰 생성");
        String targetUrl = getTargetUrl(accessToken);
        //response.sendRedirect(targetUrl);
        // URL에 토큰값을 담아서 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }



    // 생성된 리프레시 토큰을 전달받아 데이터베이스에 저장
    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository
                .findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(userId, newRefreshToken));
        refreshTokenRepository.save(refreshToken);
    }

    // 생성된 리프레시 토큰을 쿠키에 저장
    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME );
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }
}
