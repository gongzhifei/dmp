package com.xinxindai.user.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinxindai.user.util.DomainLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gongzhifei
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);

    @Autowired
    private DomainLogin domainLogin;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter(JWTLoginFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        String password = request.getParameter(JWTLoginFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }
        username = username.trim();
        LOGGER.info("用户：{}正在访问!", username);

        boolean flag = username.endsWith("@xinxindai.com");
        String domainName = flag ? username : username + "@xinxindai.com";
        String dmpUserName = flag ? username.substring(0, username.length() - 14) : username;
        //域登录验证
        int result = domainLogin.connectAD(domainName, password);
        if (result == 0) {
            throw new BadCredentialsException("Authentication Failed!");
        } else if (result == 1) {
            //由于密码不通过数据库验证，返回固定密码比对
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    dmpUserName, "e10adc3949ba59abbe56e057f20f883e");
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else if (result == -1) {
            throw new AuthenticationServiceException("AD domain connection failed!");
        } else {
            throw new AuthenticationServiceException("Unknown authentication!");
        }
    }
}
