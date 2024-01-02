package com.example.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebImageConfig implements WebMvcConfigurer {

    private String resourcePath = "/upload/**"; //뷰 에서 접근할 경로
    private String savePath = "file:///C:/springboot_img/"; //실 파일 저장 위치

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }
}
