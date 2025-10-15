package com.example.task.common;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.task.persistence.entity.User;
import com.example.task.persistence.mapper.UserMapper;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.selectByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username+"用户不存在");
        }
        return new CustomerUserDetails(user.getId(), user.getUsername(), user.getPassword(), Collections.emptyList());
    }

}
