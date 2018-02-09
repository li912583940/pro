package com.sl.ue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 描述 [处理跨域请求]
 * 创建者 LXT
 * 创建时间 2017年12月27日 下午1:59:06
 */
@Configuration
public class CorsApiConfigurer extends WebMvcConfigurerAdapter{

	@Override
	public void addCorsMappings(CorsRegistry registry){
		System.out.println("自动加载");
		//处理所有请求
		registry.addMapping("/**");
	
		//处理特定的请求
//		registry.addMapping("/use/**")
//        .allowedOrigins("http://128.0.0.2")
//        .allowedMethods("GET", "POST")
//        .allowCredentials(false).maxAge(3600);

	}
}
