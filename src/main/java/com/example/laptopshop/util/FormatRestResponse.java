package com.example.laptopshop.util;

import com.example.laptopshop.model.RestResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // áp dụng cho mọi response
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body,
            @NonNull MethodParameter returnType,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {

        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();

        // 🚫 Không wrap nếu đã là RestResponse
        if (body instanceof RestResponse) {
            return body;
        }

        // 🚫 Không wrap String (tránh lỗi cast)
        if (body instanceof String) {
            return body;
        }

        // ✅ Tạo response chuẩn
        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(status);
        res.setStatus(HttpStatus.valueOf(status).is2xxSuccessful() ? "success" : "error");

        if (status >= 400) {
            // lỗi thì trả raw (cho GlobalExceptionHandler xử lý)
            return body;
        } else {
            res.setData(body);
            res.setMessage("CALL API SUCCESS");
        }

        return res;
    }
}
