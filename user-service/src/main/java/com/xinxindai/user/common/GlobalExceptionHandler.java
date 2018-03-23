package com.xinxindai.user.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gongzhifei
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class); 
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ExceptionHandler(value = Throwable.class)
	public RestResponse<Object> handler(HttpServletRequest request,Throwable throwable){
		LOGGER.error(throwable.getMessage(),throwable);
		RestCode restCode = ExceptionCodeRepo.getRestCode(throwable);
		RestResponse<Object> response = new RestResponse<Object>(restCode.code, restCode.msg);
		return response;
	}
	
}
