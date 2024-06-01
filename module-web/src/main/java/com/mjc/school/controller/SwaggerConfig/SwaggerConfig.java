package com.mjc.school.controller.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mjc.school.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(appInfo());

    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title("Small news management application")
                .description("Small news management application using REST API")
                .version("1.0")
                .build();
    }
}
