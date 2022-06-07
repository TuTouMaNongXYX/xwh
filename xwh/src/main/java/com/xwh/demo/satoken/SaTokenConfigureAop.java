package com.xwh.demo.satoken;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 谢宇轩 注解
 * @Description: TODO
 * @DateTime: 2022/6/2 9:55
 **/
@Configuration
public class SaTokenConfigureAop implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/user/login",
                "/favicon.ico",
                "/*.html",
                "/**/*.js",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-ui.html",
                "/doc.html",
                "/webjars/**",
                "/v2/**",
                "/v3/**",
                "/webjars/*",
                "/swagger-resources/**",
                "/swagger-ui/**");

    }
}