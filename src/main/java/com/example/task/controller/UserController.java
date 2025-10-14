package com.example.task.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.task.common.ApiResponse;
import com.example.task.common.CustomerUserDetails;
import com.example.task.persistence.dto.userDto.ChangePasswordDTO;
import com.example.task.persistence.dto.userDto.UserInfoDTO;
import com.example.task.persistence.dto.userDto.UserLoginRequestDTO;
import com.example.task.persistence.dto.userDto.UserLoginResponseDTO;
import com.example.task.persistence.dto.userDto.UserRegisterDTO;
import com.example.task.persistence.service.IUserService;

import jakarta.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    
    @Autowired
    private IUserService userService;
    // 用户注册
    //@Valid 用于启用请求体的验证，确保传入的数据符合预定义的约束条件。
    //也就是说，在处理用户注册请求时，系统会自动检查 UserRegisterDTO 对象中的字段是否满足其上的验证注解（如@NotNull, @Size等）所定义的规则。如果验证失败，Spring 会抛出相应的异常，通常会导致返回一个 400 Bad Request 响应，提示客户端请求的数据有误。
    @PostMapping
    public ApiResponse<String> userRegister(@Valid @RequestBody UserRegisterDTO userRegisterDTO){

        userService.register(userRegisterDTO);
        return ApiResponse.success("注册成功");
    }
    // 用户登录
    @PostMapping("/login")
    public ApiResponse<UserLoginResponseDTO> userLogin(@Valid @RequestBody UserLoginRequestDTO userRegisterDTO){
        UserLoginResponseDTO responseDTO = userService.login(userRegisterDTO);
        return ApiResponse.success(responseDTO,"登录成功");
    }
    // 获取用户信息
    @GetMapping("/info")
    public ApiResponse<UserInfoDTO> userInfo(@AuthenticationPrincipal CustomerUserDetails userDetails){
        Long userId =userDetails.getId(); // 从认证信息中获取用户ID
        UserInfoDTO userInfoDTO = userService.getUserInfo(userId);
        return ApiResponse.success(userInfoDTO,"获取用户信息成功");
    }

    // 修改密码
    @PutMapping("/password")
    public ApiResponse<String> changePassword(@AuthenticationPrincipal CustomerUserDetails userDetails, @Valid @RequestBody ChangePasswordDTO dto){
        Long userId =userDetails.getId(); // 从认证信息中获取用户ID
        userService.changePassword(userId, dto.getOldPassword(), dto.getNewPassword());
        return ApiResponse.success("修改密码成功");
    }
}

