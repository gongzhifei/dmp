package com.xinxindai.user.common;

/**
 * 返回码
 * @author gongzhifei
 */

public enum RestCode {
	OK(200,"OK"),
	LOGIN_FAILED(400,"登录失败"),
	UNAUTHORIZED(401,"请前往登陆"),
	UNKNOWN_ERROR(500,"服务器异常"),
	NOT_ALLOW_ACCESS(405,"访问权限不足"),
	WRONG_PAGE(2,"页面不存在");

	public final int code;
	public final String msg;
	
	private RestCode(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
}
