package com.example.task.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置类
 * 配置 Spring Security 的各种安全设置，包括密码编码器、安全过滤链和认证管理器。
 * @author 陈高明
 *
 */
@Configuration
public class SecurityConfig {

    //密码编码器 Bean
    //passwordEncoder 方法定义了一个 BCryptPasswordEncoder 实例，用于对用户密码进行加密和验证。
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全规则，安全过滤链 Bean
    //securityFilterChain 方法配置了应用的安全规则，而 authenticationManager 方法提供了一个认证管理器，用于处理用户的登录请求。
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())//禁用CSRF保护。CSRF 是针对表单提交的攻击，JWT 是无状态的，不依赖 Cookie，所以可以安全地关闭。
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 设置会话管理为无状态，因为我们使用 JWT 进行认证，不需要session
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/user/login", "/admin/user/register").permitAll()// 允许未认证用户访问登录和注册端点
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)// 在用户名密码认证过滤器之前添加 JWT 认证过滤器
            .build();
    }
    // 认证管理器 Bean，用于登录接口认证
    //securityFilterChain 方法配置了应用的安全规则，而 authenticationManager 方法提供了一个认证管理器，用于处理用户的登录请求。
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
