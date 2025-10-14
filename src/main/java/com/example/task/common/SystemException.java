package com.example.task.common;


/**
 * 系统异常类
 * 用于表示系统级别的异常情况
 * @author 陈高明
 * @since 2025-10-11
 */
public class SystemException extends RuntimeException {
    
    private int code;
    //使用状态码和消息构造异常
    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }
    
    //使用枚举构造异常
    public SystemException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public int getCode() {
        return code;
    }

}
