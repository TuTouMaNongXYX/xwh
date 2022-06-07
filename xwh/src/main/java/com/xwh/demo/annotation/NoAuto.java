package com.xwh.demo.annotation;

import java.lang.annotation.*;

/**
 * @Author: 谢宇轩  去除日志注解
 * @Description: TODO
 * @DateTime: 2022/6/2 10:51
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NoAuto {
}
