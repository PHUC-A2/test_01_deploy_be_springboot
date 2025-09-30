package com.example.laptopshop.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.laptopshop.model.RestResponse;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(
            // @Nullable
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        // dùng cái này để lấy mã phản hồi
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus(); // lấy trạng thái code

        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(status); // lấy mã lỗi bất kể đúng hay sai
        res.setStatus(HttpStatus.valueOf(status).is2xxSuccessful() ? "success" : "error");
        if (status >= 400) {
            // đây là đoạn lỗi 
            return body;

        } else {

            res.setData(body); // data chính là phần body
            res.setMessage("CALL API SUCCESS");
        }
        return res; // trả format chuẩn khi thành công
    }

}
