package com.example.task.persistence.dto.userDto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户信息DTO
 * @author 陈高明
 *
 */
@Data
public class UserInfoDTO {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
