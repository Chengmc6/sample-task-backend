package com.example.task.persistence.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 修改密码DTO
 * Data Transfer Object数据传输对象
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
public class ChangePasswordDTO {
    
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6,max = 18, message = "新密码长度不能少于6位")
    private String newPassword;
}
