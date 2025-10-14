package com.example.task.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一API响应结果封装
 * 泛型T表示响应数据的类型
 * @author 陈高明
 * @since 2025-10-11
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    // 静态泛型方法，简化响应对象的创建
    // 成功的响应
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "Success", null);
    }
    // 成功的响应，带数据
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }
    // 成功的响应，带数据和自定义消息
    public static <T> ApiResponse<T> success(T data,String message){
        return new ApiResponse<>(200, message, data);
    }
    // 失败的响应，带错误码和消息
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
    // 失败的响应，带默认错误码和自定义消息
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message, null);
    }
    // 失败的响应，带ResultCode枚举
    public static <T> ApiResponse<T> error(ResultCode resultCode) {
        return new ApiResponse<>(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
