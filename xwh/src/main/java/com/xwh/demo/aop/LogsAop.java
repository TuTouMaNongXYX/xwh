package com.xwh.demo.aop;

import cn.dev33.satoken.util.SaFoxUtil;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 谢宇轩  日志切面
 * @Description: TODO
 * @DateTime: 2022/6/2 10:49
 **/
@Aspect
@Component
@Slf4j
public class LogsAop {

    //需要注入log的dao层



    /**
     * 切入点
     */
    @Pointcut("execution(* com.xwh.*.controller.*.*(..)) && !@annotation(com.xwh.demo.annotation.NoAuto)")
    public void log() {
    }


    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String header = request.getHeader("User-Agent");

        Class<?> classTarget = point.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getMethod(point.getSignature().getName(), par);
        String apiOperationValue = objMethod.getAnnotation(ApiOperation.class).value();

        //日志
//        SystemLogs logs = SystemLogs.builder()
//                .logId(NumberUtil.getUUIDTOLong().toString())
//                .threadId(Long.toString(Thread.currentThread().getId()))
//                .threadName(Thread.currentThread().getName())
//                .ip(getIp(request))
//                .url(request.getRequestURL().toString())
//                .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
//                        point.getSignature().getName()))
//                .httpMethod(request.getMethod())
//                .methodName(apiOperationValue)
//                .requestParams(getNameAndValue(point).toString())
////                .result(result.toString().length()>5000?result.toString().substring(0,5000):result.toString())
//                .result(result.toString())
//                .timeCost(System.currentTimeMillis() - startTime)
//                .userAgent(header)
//                .logTime(new Timestamp(System.currentTimeMillis()))
//                .build();
//        logsMapper.insert(logs);
        return result;
    }


    /**
     * 获取方法参数名和参数值
     *
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (SaFoxUtil.isEmpty(names) || SaFoxUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String localhost = "127.0.0.1";
        String localhost2 = "0:0:0:0:0:0:0:1";
        // 获取到多个ip时取第一个作为客户端真实ip
        if (StringUtils.isNotEmpty(ip) && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            if (ArrayUtils.isNotEmpty(ipArray)) {
                ip = ipArray[0];
            }
        }
        if (localhost.equals(ip) || localhost2.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }


}
