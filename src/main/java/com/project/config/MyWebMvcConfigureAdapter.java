package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.project.interceptor.ManagerInterceptor;

/**
 * 
 * @author s
 *   springmvc 拦截器配置
 */




@Configuration
public class MyWebMvcConfigureAdapter implements WebMvcConfigurer{
	
	@Bean
	public ManagerInterceptor ManagerInterceptor()
	{
		return new ManagerInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ManagerInterceptor()).addPathPatterns("/manager");
		
	}

	
}
