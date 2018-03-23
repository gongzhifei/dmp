package com.xinxindai.user.controller;

import com.xinxindai.user.common.RestCode;
import com.xinxindai.user.common.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gongzhifei
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public RestResponse<String> hello(){
        return new RestResponse(RestCode.OK.code,RestCode.OK.msg);
    }

}
