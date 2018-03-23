package com.xinxindai.api.controller;

import com.xinxindai.api.common.RestResponse;
import com.xinxindai.api.config.http.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    GenericRest rest;

    @GetMapping("/hello")
    public RestResponse<String> test(@RequestHeader("token") String token){
        String url = "http://user-service/hello";
        return rest.get(url, null, new ParameterizedTypeReference<RestResponse<String>>() {},token).getBody();
    }

}
