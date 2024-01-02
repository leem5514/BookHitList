package com.example.project.config.auth;

import com.example.project.config.TokenProvider;
import com.example.project.entity.RefreshToken;
import com.example.project.entity.UserEntity;
import com.example.project.repository.RefreshTokenRepository;
import com.example.project.service.UserService;
import com.example.project.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository;
    private final UserService userService;

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);

    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    public static final String REDIRECT_PATH = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User Auth2User= (OAuth2User) authentication.getPrincipal();
        UserEntity user = userService.findByEmail((String) Auth2User.getAttributes().get("email"));
        System.out.println("토큰 생성중");
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        saveRefreshToken(user.getId(), refreshToken);
        addRefreshTokenCookie(request, response, refreshToken);

        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
        String targetUrl = getTargetUrl(accessToken);

        //쿠키 제거, 인증관련 설정값
        clearAuthenticationAttribute(request, response);

        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);



    }

    private void clearAuthenticationAttribute(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        oAuth2AuthorizationRequestBasedOnCookieRepository.removeAuthorizationRequest(request, response);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toString();
    }

    private void saveRefreshToken(Long userId, String newRefershToken) {
        RefreshToken refreshToken = refreshTokenRepository
                .findByUserId(userId)
                .map(entity -> entity.update(newRefershToken))
                .orElse(new RefreshToken(userId, newRefershToken));
        refreshTokenRepository.save(refreshToken);
    }

    private void addRefreshTokenCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

}
