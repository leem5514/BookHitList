//package com.example.project.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    // 스프링 시큐리티의 모든 기능을 사용하지 않게 설정하는 코드
//    // 즉, 인가 인증을 모두 적용하지 않는다.
//
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**")));
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((request) -> {
//                    request.requestMatchers(antMatcher("/")).permitAll();
//                    request.requestMatchers(antMatcher("/login")).permitAll();
//                    request.requestMatchers(antMatcher("/signup")).permitAll();
//                    request.requestMatchers(antMatcher("/user")).permitAll();
//                    request.requestMatchers(antMatcher("/img/**")).permitAll();
//                    request.requestMatchers(antMatcher("/css/**")).permitAll();
//                    request.requestMatchers(antMatcher("/font/**")).permitAll();
////                    request.requestMatchers(antMatcher("/js/**")).permitAll();
//
//
//                    request.anyRequest().permitAll();
//                });
//
////        // 로그인 설정
////        http.formLogin(login -> login // form 방식 로그인 사용
////                .loginPage("/login") // 로그인 페이지 경로 설정
////                .defaultSuccessUrl("/", true) //성공 시 이동할 경로 설정
////                .permitAll()  // 이동이 막히면 안되므로 허용
////        );
////
////        // 로그아웃 설정
////        http.logout(logout -> logout
////                .logoutSuccessUrl("/login")
////                .invalidateHttpSession(true));
//
//
//
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
