package com.example.project.controller;

import com.example.project.config.TokenProvider;
import com.example.project.dto.AddUserRequest;
import com.example.project.entity.UserEntity;
import com.example.project.handle.LoginHandler;
import com.example.project.service.MypageService;
import com.example.project.service.TokenService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final MypageService mypageService;
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;
    private final LoginHandler loginHandler;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        mypageService.save(request.getEmail());
        return "redirect:/";
    }

    @PostMapping("/login")
    public void login(@ModelAttribute AddUserRequest req, HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginHandler.login(req, request, response);
//        return "redirect:/";
    }


}
