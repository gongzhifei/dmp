package com.xinxindai.api.config.http;

import javax.annotation.Resource;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * restTemplate 模板类
 * @author gongzhifei
 */
@Service
public class GenericRest {
	
	@Resource(name="loadRestTemplate")
	private RestTemplate loadRestTemplate;
	
	@Resource(name="directRestTemplate")
	private RestTemplate directRestTemplate;
	
	private static final String directFlag = "direct://";
	
	/**
	 * POST
	 * @param url
	 * @param requestBody
	 * @param responseType 包装类型的参数 泛型不能通过.class表示需该参数进行包装 
	 * @return
	 */
	public <T> ResponseEntity<T> post(String url, Object requestBody,ParameterizedTypeReference<T> responseType){
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		return template.exchange(url, HttpMethod.POST,new HttpEntity(requestBody),responseType);
	}
	public <T> ResponseEntity<T> post(String url, Class<T> responseType, Object param){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(param,headers);
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		return template.exchange(url, HttpMethod.POST, httpEntity, responseType);
	}

	/**
	 * GET
	 * @param url
	 * @param requestBody
	 * @param responseType
	 * @return
	 */
	public <T> ResponseEntity<T> get(String url, Object requestBody,ParameterizedTypeReference<T> responseType){
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		return template.exchange(url, HttpMethod.GET,HttpEntity.EMPTY,responseType);
	}

	/**
	 * GET
	 * @param url
	 * @param requestBody
	 * @param responseType
	 * @return
	 */
	public <T> ResponseEntity<T> get(String url, Object requestBody,ParameterizedTypeReference<T> responseType,String token){
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		HttpHeaders headers = new HttpHeaders();
		headers.set("token",token);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		return template.exchange(url, HttpMethod.GET,httpEntity,responseType);
	}
	
	/**
	 * 区分RestTemplate是否需要负载
	 * @param url
	 * @return
	 */
	private RestTemplate getRestTemplate(String url) {
		if(url.contains(directFlag)){
			return directRestTemplate;
		}else{
			return loadRestTemplate;
		}
	}
	
	

}
