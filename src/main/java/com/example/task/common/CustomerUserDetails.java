package com.example.task.common;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义的UserDetails实现类，包含用户ID、用户名、密码和权限信息
 * UserDetails是Spring Security用于表示用户信息的核心接口
 * 实现该接口可以让Spring Security识别和处理用户的认证和授权信息
 * @author gaoming
 *
 */
@Getter
@AllArgsConstructor
public class CustomerUserDetails implements UserDetails{
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;// 用户权限信息,用于后续扩展

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期，true表示未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定，true表示未锁定
    @Override  
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证是否未过期，true表示未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否启用，true表示启用
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
