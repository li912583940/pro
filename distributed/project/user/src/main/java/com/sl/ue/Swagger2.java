package com.sl.ue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述 [swagger2配置]
 * 创建者 LXT
 * 创建时间 2017年7月27日 上午11:26:26
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sl.ue.web"))
				.paths(PathSelectors.any())
				.build();
	}
	
	public ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("评论RESTful APIs")
				.description("详情咨询上仙")
				.termsOfServiceUrl("http://192.168.1.91:8080/swagger-ui.html")
				.contact("帅帅天")
				.version("1.0")
				.build();
	}
}
