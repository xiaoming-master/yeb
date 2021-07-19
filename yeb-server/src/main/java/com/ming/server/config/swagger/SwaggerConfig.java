package com.ming.server.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 * @Author: ming
 * @create: 2021-07-19 09:55
 * @program: yeb
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ming.server.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云E办接文档")
                .description("云E办接文档")
                .contact(new Contact("ming", "http://localhost:8081/doc.html", "1186927930@qq.com"))
                .version("1.0")
                .build();
    }

    public List<SecurityContext> securityContexts() {
        //设置需要登陆认证的路径
        ArrayList<SecurityContext> res = new ArrayList<>();
        res.add(getContextByPath("/"));
        return res;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        ArrayList<SecurityReference> res = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");

        res.add(new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
        return res;
    }

    public List<ApiKey> securitySchemes() {
        //设置请求头信息
        ArrayList<ApiKey> res = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
        res.add(apiKey);
        return res;
    }
}
