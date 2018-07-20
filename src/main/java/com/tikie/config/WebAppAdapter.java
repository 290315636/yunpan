/**
 * 
 */
package com.tikie.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tikie.interceptor.VCInterceptor;

/**
 * @author zhaocs
 *
 */
@SuppressWarnings("deprecation")
public class WebAppAdapter extends WebMvcConfigurerAdapter{
	@Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        //注册自定义拦截器，添加拦截路径和排除拦截路径  
        registry.addInterceptor(new VCInterceptor()).addPathPatterns("api/download/**").excludePathPatterns("api/direct/**");  
    }  
}
