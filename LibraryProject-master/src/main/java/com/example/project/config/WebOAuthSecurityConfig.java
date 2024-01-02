package com.example.project.config;

import com.example.project.config.auth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.example.project.config.auth.OAuth2SuccessHandler;
import com.example.project.config.auth.OAuth2UserCustomService;
import com.example.project.repository.RefreshTokenRepository;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@RequiredArgsConstructor
public class WebOAuthSecurityConfig {

    private final TokenProvider tokenProvider;
    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**")));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> {
                    request.requestMatchers(antMatcher("/user")).permitAll();
                    request.requestMatchers(antMatcher("/login")).permitAll();
                    request.requestMatchers(antMatcher("/js/**")).permitAll();
                    request.requestMatchers(antMatcher("/img/**")).permitAll();
                    request.requestMatchers(antMatcher("/css/**")).permitAll();
                    request.requestMatchers(antMatcher("/vendor/**")).permitAll();
                    request.requestMatchers(antMatcher("/api/token")).permitAll();
                    request.requestMatchers(antMatcher("/")).permitAll();
                    request.requestMatchers(antMatcher("/signup")).permitAll();
                    request.requestMatchers(antMatcher("/font/**")).permitAll();
                    request.requestMatchers(antMatcher("/api/**")).permitAll();
                    request.anyRequest().permitAll();
                });
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 헤더를 확인할 커스텀 필터 추가
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login(oauth2 -> {
            oauth2.loginPage("/login")
                    .authorizationEndpoint(
                            authEndPoint ->
                                authEndPoint.authorizationRequestRepository(
                                    oAuth2AuthorizationRequestBasedOnCookieRepository()))
                    .userInfoEndpoint(
                            userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(
                                    oAuth2UserCustomService));
            // 인증 성공 시 실행할 핸들러
            oauth2.successHandler(oAuth2SuccessHanlder());
            }
        );

        http.logout(logout -> logout.logoutSuccessUrl("/login"));

        // /api 로 시작하는 url인 경우 401상태 코드를 반환하도록 예외처리
        http.exceptionHandling(exceptionHandlingConfigurer -> {
            exceptionHandlingConfigurer.defaultAuthenticationEntryPointFor(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/api/**"));
        });
        return http.build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHanlder() {
        return new OAuth2SuccessHandler(tokenProvider, refreshTokenRepository, oAuth2AuthorizationRequestBasedOnCookieRepository(), userService);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
