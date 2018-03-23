package com.xinxindai.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinxindai.user.common.RestCode;
import com.xinxindai.user.common.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gongzhifei
 */
@Component
public class MyAccessDenied implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        RestResponse result = new RestResponse(RestCode.NOT_ALLOW_ACCESS.code,RestCode.NOT_ALLOW_ACCESS.msg);
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().close();
    }
}
