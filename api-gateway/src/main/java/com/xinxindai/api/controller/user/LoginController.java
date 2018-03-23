package com.xinxindai.api.controller.user;

import com.xinxindai.api.common.RestResponse;
import com.xinxindai.api.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author gongzhifei
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String doLogin(HttpServletResponse responsee,String username, String password){
        ResponseEntity<String> result = loginService.doLogin(username,password);
        HttpHeaders headers = result.getHeaders();
        List<String> tokenList = headers.get("token");
        responsee.setHeader("token",tokenList.get(0));
        return result.getBody();
    }

}
