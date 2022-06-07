package com.xwh.demo.Exception;

import lombok.NoArgsConstructor;

/**
 * @Author: 谢宇轩 自定义异常
 * @Description: TODO
 * @DateTime: 2022/6/1 16:39
 **/
@NoArgsConstructor
public class MyException extends Exception {


    /**
     * 有参的构造方法
     * @param message
     */
    public MyException(String message){
        super(message);
    }

    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param message
     * @param cause
     */
    public MyException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 用指定原因构造一个新的异常
     * @param cause
     */
    public MyException(Throwable cause) {
        super(cause);
    }
}
