package com.xinxindai.api.common;

/**
 * 返回码
 * @author gongzhifei
 */

public enum RestCode {
	OK(0,"OK"),
	UNKNOWN_ERROR(1,"服务器异常"),
	WRONG_PAGE(2,"页面不存在");
	
	public final int code;
	public final String msg;
	
	private RestCode(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
}
