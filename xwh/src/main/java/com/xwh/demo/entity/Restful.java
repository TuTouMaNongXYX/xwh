package com.xwh.demo.entity;

import com.xwh.demo.entity.em.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @Author: 谢宇轩 前后端协议类
 * @Description: TODO
 * @DateTime: 2022/6/1 16:14
 **/
@Data
@ApiModel(value = "Response对象", description = "全局统一返回结果")
public class Restful<T> implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "状态码")
    private String code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Restful() {
    }

    public Restful(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Restful(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return
     * @description 成功无数据
     * @author 谢宇轩
     * @date 2022/6/1 16:22
     * @params
     */
    public static <T> Restful<T> ok() {
        return new Restful<T>(ResultCodeEnum.SUCCESS.getFlag(),
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage());
    }


    /**
     * @return
     * @description 成功有数据
     * @author 谢宇轩
     * @date 2022/6/1 16:26
     * @params
     */
    public static <T> Restful<T> ok(T data) {
        return new Restful<T>(ResultCodeEnum.SUCCESS.getFlag(),
                ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), data);
    }


    /**
     * @return
     * @description 警告
     * @author 谢宇轩
     * @date 2022/6/1 16:27
     * @params
     */
    public static <T> Restful<T> warning() {
        return new Restful<T>(ResultCodeEnum.WARNING.getFlag(),
                ResultCodeEnum.WARNING.getCode(),
                ResultCodeEnum.WARNING.getMessage());
    }


     /** @description 无效请求
     * @author 谢宇轩
     * @date 2022/6/1 16:28
     * @params
     * @return
     */
     public static <T> Restful<T> no() {
         return new Restful<T>(ResultCodeEnum.INVALID_REQUEST.getFlag(),
                 ResultCodeEnum.INVALID_REQUEST.getCode(),
                 ResultCodeEnum.INVALID_REQUEST.getMessage());
     }

    /**
     * 系统异常
     */
    public static<T> Restful<T> error(T data){
        return new Restful<T>(ResultCodeEnum.ERROR.getFlag(),
                ResultCodeEnum.ERROR.getCode(),
                ResultCodeEnum.ERROR.getMessage(),data);
    }

    /**
     * 系统异常
     */
    public static<T> Restful<T> error(){
        return new Restful<T>(ResultCodeEnum.ERROR.getFlag(),
                ResultCodeEnum.ERROR.getCode(),
                ResultCodeEnum.ERROR.getMessage());
    }

    public Restful<T> data(T data){
        this.setData(data);
        return this;
    }
    public Restful<T> message(String message){
        this.setMessage(message);
        return this;
    }

    public Restful<T> code(String code){
        this.setCode(code);
        return this;
    }



}
