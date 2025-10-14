package com.example.task.common;

import lombok.Getter;
/**
 * 统一API响应状态码
 * 定义了一组标准的响应码和对应的信息
 * 可用于整个应用的API响应，确保一致性
 * @author 陈高明
 *
 */
@Getter
public enum ResultCode {
    //200 代表成功
    //400 代表失败
    //500 代表系统异常
    //1000 以上为自定义业务异常
    SUCCESS(200, "成功"),
    FAIL(400, "请求失败"),
    VALIDATION_ERROR(422, "参数校验失败"),
    UNAUTHORIZED(401, "未登录或权限不足"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    SERVER_ERROR(500, "系统异常"),
    USERNAME_EXISTS(1001, "用户名已存在"),
    EMAIL_EXISTS(1002, "邮箱已注册"),
    PASSWORD_ERROR(1003, "密码错误"),
    USER_NOT_FOUND(1004, "用户不存在"),
    PASSWORD_SAME(1005, "新旧密码不能相同");
    // 其他业务异常码可以继续添加

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
