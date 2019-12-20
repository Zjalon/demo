package com.jalon.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:8080/swagger-ui.html
 */
@Configurable
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jalon.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("demo swagger title")
                .contact(new Contact("张健龙", "1642422726@qq.com", "1642422726@qq.com"))
                .version("1.0")
                .description("描述")
                .build();
    }
}
