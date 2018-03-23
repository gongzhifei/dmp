package com.xinxindai.api.config.http;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/**
 * restTemplate配置类
 *
 * @author gongzhifei
 */
@Configuration
public class RestAutoConfig {

    /**
     * 支持负载的restTemplate
     *
     * @param httpClient
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate loadRestTemplate(HttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.getMessageConverters().add(1, new FastJsonHttpMessageConverter5());
        return restTemplate;
    }

    /**
     * 直连的restTemplate
     *
     * @param httpClient
     * @return
     */
    @Bean
    RestTemplate directRestTemplate(HttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.getMessageConverters().add(1, new FastJsonHttpMessageConverter5());
        return restTemplate;
    }


    public static class FastJsonHttpMessageConverter5 extends FastJsonHttpMessageConverter4 {

        static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

        public FastJsonHttpMessageConverter5() {
            setDefaultCharset(DEFAULT_CHARSET);
            setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, new MediaType("application", "*+json")));
        }

    }

}
