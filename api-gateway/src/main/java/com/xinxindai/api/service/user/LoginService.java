package com.xinxindai.api.service.user;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.xinxindai.api.common.RestResponse;
import com.xinxindai.api.config.http.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;


/**
 * @author gongzhifei
 */
@Service
public class LoginService {

    @Autowired
    private GenericRest genericRest;

    public ResponseEntity<String>  doLogin(String username,String password){
        String url = "http://user-service/login";
        MultiValueMap<String,String> param = new LinkedMultiValueMap<>();
        param.add("username",username);
        param.add("password",password);
        ResponseEntity<String> result = genericRest.post(url,String.class,param);
        return result;
    }

}
