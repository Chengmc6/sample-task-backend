package com.example.task.persistence.dto.userDto;

import lombok.Data;

/**
 * 用户登录响应DTO
 * @author 陈高明
 *
 */
@Data
public class UserLoginResponseDTO {
    private Long id;
    private String username;
    private String token;//后续拓展用户登录令牌
}
