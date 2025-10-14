package com.example.task.persistence.service.impl;

import com.example.task.common.CustomerUserDetails;
import com.example.task.common.JwtUtils;
import com.example.task.common.ResultCode;
import com.example.task.common.SystemException;
import com.example.task.persistence.dto.userDto.UserInfoDTO;
import com.example.task.persistence.dto.userDto.UserLoginRequestDTO;
import com.example.task.persistence.dto.userDto.UserLoginResponseDTO;
import com.example.task.persistence.dto.userDto.UserRegisterDTO;
import com.example.task.persistence.entity.User;
import com.example.task.persistence.mapper.UserMapper;
import com.example.task.persistence.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    // 用户注册
    @Override
    public void register(UserRegisterDTO userRegisterDTO){
        
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userRegisterDTO.getUsername());
        if(userMapper.selectCount(queryWrapper)>0){
            throw new SystemException(ResultCode.USERNAME_EXISTS);
        }

        //密码加密
        String encodePwd=passwordEncoder.encode(userRegisterDTO.getPassword());
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(encodePwd);
        user.setEmail(userRegisterDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        userMapper.insert(user);
    }
    // 用户登录
    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO requestDTO) {
        // 构造认证对象，使用AuthenticationManager进行认证,认证失败会抛出异常
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());
        // 调用Security的认证流程，进行认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // 认证成功后，从认证对象中获取用户信息
        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();

        // 生成token（这里简单返回用户ID作为示例，实际应用中应生成JWT或其他形式的token）
        //String token = "token-" + user.getId();
        String token = JwtUtils.getToken(userDetails.getId(), userDetails.getUsername());
        // 返回登录响应DTO
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setId(userDetails.getId());
        responseDTO.setUsername(userDetails.getUsername());
        responseDTO.setToken(token);

        return responseDTO;
    }
    // 获取用户信息
    @Override
    public UserInfoDTO getUserInfo(Long userId) {
        User user = userMapper.selectById(userId);
        // 用户不存在
        if(user==null){
            throw new SystemException(ResultCode.USER_NOT_FOUND);
        }
        // 转换为DTO，不直接使用实体类，防止暴露敏感信息
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(user.getId());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setCreatedAt(user.getCreatedAt());
        return userInfoDTO;
    }
    // 修改密码
    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user=userMapper.selectById(userId);
        // 用户不存在
        if(user==null){
           throw new SystemException(ResultCode.USER_NOT_FOUND) ;
        }
        // 旧密码错误
        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new SystemException(ResultCode.PASSWORD_ERROR);
        }
        // 新旧密码相同
        if(passwordEncoder.matches(newPassword, user.getPassword())){
            throw new SystemException(ResultCode.PASSWORD_SAME);
        }
        // 更新密码
        String encodePwd=passwordEncoder.encode(newPassword);
        user.setPassword(encodePwd);
        userMapper.updateById(user);
    }
}
