package com.xinxindai.user.controller;

import com.xinxindai.user.common.RestCode;
import com.xinxindai.user.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GFZ
 */
@RestController
public class AuthenticationController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResponse requestAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String target = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求："+target);
        }
        return new RestResponse(RestCode.UNAUTHORIZED.code,RestCode.UNAUTHORIZED.msg);
    }

}
