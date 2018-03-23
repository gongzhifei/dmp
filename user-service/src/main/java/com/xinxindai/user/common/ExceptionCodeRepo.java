package com.xinxindai.user.common;


import com.google.common.collect.ImmutableMap;
import com.xinxindai.user.exception.IllegalParamsException;
import com.xinxindai.user.exception.WithTypeException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author gongzhifei
 */
public class ExceptionCodeRepo {
	
	private static final ImmutableMap<Object, RestCode> MAP = ImmutableMap.<Object, RestCode>builder()
			.put(IllegalParamsException.Type.WRONG_PAGE_NUM, RestCode.WRONG_PAGE)
			.put(IllegalParamsException.class, RestCode.UNKNOWN_ERROR).build();
			
	
	public static RestCode getRestCode(Throwable throwable){
		if(throwable==null){
			return RestCode.UNKNOWN_ERROR;
		}
		Object target = throwable;
		if(throwable instanceof WithTypeException){
			Object type = getType(throwable);
			if(type != null){
				target = type;
			}
		}
		RestCode restCode = MAP.get(target);
		if(restCode !=null){
			return restCode;
		}
		Throwable rootCause = ExceptionUtils.getRootCause(throwable);
		if(rootCause != null){
			return getRestCode(rootCause);
		}
		return RestCode.UNKNOWN_ERROR;
	}

	private static Object getType(Throwable throwable) {
		try{
			return FieldUtils.readDeclaredField(throwable,"type",true);
		}catch(Exception e){
			return null;
		}
	}
	

}
