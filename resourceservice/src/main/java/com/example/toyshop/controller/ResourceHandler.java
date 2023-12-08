package com.example.toyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ResourceHandler implements WebMvcConfigurer {

    @Value("${media.url}")
    private String baseMediaUrl;

//    private final String UPLOAD_DIRECTORY = "/images/"; // в docker
    private final String UPLOAD_DIRECTORY = baseMediaUrl + "/images/"; // на локалке

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + UPLOAD_DIRECTORY);
    }
}
