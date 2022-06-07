package com.xwh.demo.satoken;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 谢宇轩
 * @Description: TODO
 * @DateTime: 2022/6/1 14:59
 **/
//@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


    }


//        // 注册路由拦截器，自定义认证规则
//        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
//
//            // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//            SaRouter.match("/**", "/user/login", r -> StpUtil.checkLogin());
////            // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
////            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));
////            // 权限认证 -- 不同模块认证不同权限
////            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
////            SaRouter.match("/menu/**", r -> StpUtil.checkPermission("menu"));
////            SaRouter.match("/role/**", r -> StpUtil.checkPermission("role"));
////            SaRouter.match("/roleAuth/**", r -> StpUtil.checkPermission("roleAuth"));
//        })).addPathPatterns("/**").excludePathPatterns("/user/login",
//                "/favicon.ico",
//                "/*.html",
//                "/**/*.js",
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.js",
//                "/swagger-ui.html",
//                "/doc.html",
//                "/webjars/**",
//                "/v2/**",
//                "/v3/**",
//                "/webjars/*",
//                "/swagger-resources/**",
//                "/swagger-ui/**");

//        // 注册Sa-Token的路由拦截器
//        registry.addInterceptor(new SaRouteInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/doLogin",
//                        "/favicon.ico",
//                        "/*.html",
//                        "/**/*.js",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-ui.html",
//                        "/doc.html",
//                        "/webjars/**",
//                        "/v2/**",
//                        "/v3/**",
//                        "/webjars/*",
//                        "/swagger-resources/**");


    /**
     * 注册 [sa-token全局过滤器]
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("xwh")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff");
                });
    }

}
