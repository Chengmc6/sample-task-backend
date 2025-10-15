package com.example.task.common;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.task.persistence.entity.User;
import com.example.task.persistence.mapper.UserMapper;

/**
 * 自定义的UserDetailsService实现类，用来判断用户名是否存在
 * @author gaoming
 * @since  2025-10-11
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    //根据查询获取用户信息，保存到CustomerUserDetails里，包括id，username，password等
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.selectByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username+"用户不存在");
        }
        return new CustomerUserDetails(user.getId(), user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
