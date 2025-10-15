package com.example.task.persistence.mapper;

import com.example.task.persistence.entity.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username=#{username} and is_deleted=0")
    User selectByUsername(@Param("username") String username);
}
