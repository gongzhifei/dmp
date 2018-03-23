package com.xinxindai.user.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinxindai.user.common.RestCode;
import com.xinxindai.user.common.RestResponse;
import com.xinxindai.user.entity.UserInfo;
import com.xinxindai.user.util.JwtHelper;
import com.xinxindai.user.util.SpringRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gongzhifei
 */
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringRedisUtil redisUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter(JWTLoginFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        Map<String, String> map = new HashMap<>();
        map.put("username",userInfo.getUsername());
        map.put("authorities", objectMapper.writeValueAsString(userInfo.getAuthorities()));
        map.put("userTypes",objectMapper.writeValueAsString(userInfo.getUserTypes()));
        String token = userInfo.getId().toString()+JwtHelper.genToken(map);
        redisTemplate.opsForValue().set(username+userInfo.getId(),token);
        RestResponse result = new RestResponse(RestCode.OK.code,RestCode.OK.msg);
        response.addHeader("token", token);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
