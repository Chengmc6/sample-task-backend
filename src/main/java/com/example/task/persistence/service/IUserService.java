package com.example.task.persistence.service;

import com.example.task.persistence.dto.userDto.UserInfoDTO;
import com.example.task.persistence.dto.userDto.UserLoginRequestDTO;
import com.example.task.persistence.dto.userDto.UserLoginResponseDTO;
import com.example.task.persistence.dto.userDto.UserRegisterDTO;
import com.example.task.persistence.entity.User;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */

public interface IUserService extends IService<User> {
    void register(UserRegisterDTO userRegisterDTO);
    UserLoginResponseDTO login(UserLoginRequestDTO requestDTO);
    UserInfoDTO getUserInfo(Long userId);
    void changePassword(Long userId, String oldPassword, String newPassword);
}
