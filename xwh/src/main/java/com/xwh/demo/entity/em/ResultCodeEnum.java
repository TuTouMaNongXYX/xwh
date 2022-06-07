package com.xwh.demo.entity.em;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: 谢宇轩
 * @Description: TODO 返回状态枚举类
 * @DateTime: 2022/6/1 16:20
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(true, "200", "成功"),
    WARNING(false, "501", "警告"),
    ERROR(false, "500", "错误"),
    NOT_JUR(false, "403", "无权限"),
    NOT_LOGIN(false, "401", "未登录"),
    INVALID_REQUEST(false, "400", "无效请求");
    /**
     * 是否成功
     */
    private Boolean flag;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
}
