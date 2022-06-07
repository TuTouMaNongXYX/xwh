package com.xwh.demo.aop;

/**
 * @Author: 谢宇轩
 * @Description: TODO
 * @DateTime: 2022/6/2 10:37
 **/

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xwh.demo.annotation.NoRepeatSubmit;
import com.xwh.demo.entity.Restful;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 自定义一个切面类，利用aspect实现切入所有方法
 */
@Aspect
@Configuration
public class NoRepeatSubmitAop {

    /**
     * 重复提交判断时间为1s
     */
    private final Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.SECONDS).build();

    /**
     * 调用controller包下的任意类的任意方法时均会调用此方法
     */
    //@Around("execution(* com.company.controller.*.*(..))")
    @Around("execution(* com.xwh..*Controller.*(..)) && @annotation(nrs)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit nrs) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getSessionId();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            String key = sessionId + "-" + request.getServletPath();
            // 如果缓存中有这个url视为重复提交
            if (cache.getIfPresent(key) == null) {
                Object o = pjp.proceed();
                cache.put(key, 0);
                return o;
            } else {
                return  Restful.error().message("请勿短时间内重复操作");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return  Restful.error().message("验证重复提交时出现未知异常!");
        }

    }



}
