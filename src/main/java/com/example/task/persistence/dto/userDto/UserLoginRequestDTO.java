package com.example.task.persistence.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录请求DTO
 * 用于接收注册信息，支持分组校验
 * @author 陈高明
 *
 */
@Data
public class UserLoginRequestDTO {

    /**
     * 用户名不能为null或空字符串
     * 密码不能为null或空字符串
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
