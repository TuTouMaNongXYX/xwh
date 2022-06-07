package com.xwh.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 谢宇轩 Swagger配置类
 * @Description: TODO
 * @DateTime: 2022/5/31 17:29
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")) // 设置扫描路径
                .build();
    }
}
