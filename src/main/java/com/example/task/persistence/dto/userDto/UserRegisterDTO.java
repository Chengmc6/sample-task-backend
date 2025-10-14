package com.example.task.persistence.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册DTO
 * @author 陈高明
 *
 */
@Data
public class UserRegisterDTO {

    //NotBlank: 不能为空, Size: 长度限制, Email: 邮箱格式
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 18, message = "密码长度不能少于6位")
    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;
}
