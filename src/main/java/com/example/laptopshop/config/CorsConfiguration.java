package com.example.laptopshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                .allowedOrigins("http://localhost:3000", "http://localhost:5173", "https://laptopshop-fe.onrender.com") // Danh
                // sách origins được phép
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương
                // thức HTTP được phép
                .allowedHeaders("*") // Cho phép tất cả headers
                .allowCredentials(true) // Cho phép gửi cookie hoặc thông tin xác thực
                .maxAge(3600); // Thời gian cache CORS preflight request (giây)
    }
}
