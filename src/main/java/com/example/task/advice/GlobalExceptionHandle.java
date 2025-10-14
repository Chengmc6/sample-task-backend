package com.example.task.advice;

import java.util.Optional;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.task.common.ApiResponse;
import com.example.task.common.ResultCode;
import com.example.task.common.SystemException;

/**
 * 全局异常处理
 * 捕获并处理应用中的各种异常，返回统一的API响应格式
 * @author 陈高明
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    //处理自定义业务异常
    @ExceptionHandler(SystemException.class)
    public ApiResponse<String> handleSystemException(SystemException ex) {
        return ApiResponse.error(ex.getCode(), ex.getMessage());

    }

    //处理参数校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleIllegalArgumentException(MethodArgumentNotValidException ex) {
        //String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        //避免空指针null
        String message = Optional.ofNullable(ex.getBindingResult().getFieldError())
                         .map(FieldError::getDefaultMessage)
                         .orElse("参数校验失败");
        return ApiResponse.error(ResultCode.VALIDATION_ERROR.getCode(), message);
    }

    //处理其他异常
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception ex) {
        ex.printStackTrace();//日志打印
        return ApiResponse.error(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage());
    }   
}
