package com.example.task.common;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT 认证过滤器
 * 该过滤器在每次请求时执行，检查请求头中的 JWT，如果有效则将用户信息存储在 Spring Security 的上下文中。
 * @author 陈高明
 * @since 2025-10-11
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 从请求头中提取JWT
        String token=JwtUtils.extractToken(request.getHeader("Authorization"));
        // 验证JWT的有效性
        if(token!=null && !JwtUtils.isTokenExpired(token)){
            Long userId=JwtUtils.parseToken(token);
            String username=JwtUtils.getUsername(token);
            CustomerUserDetails userDetails=new CustomerUserDetails(
                    userId,
                    username,
                    null,
                    Collections.emptyList()// 这里假设没有权限,可以根据需要修改
            );
            // 将用户ID和用户名存储在请求属性中，供后续处理使用

            //setAuthentication 方法将用户信息存储在 Spring Security 的上下文中，表示用户已通过身份验证。
            // 这样，后续的请求处理可以通过 SecurityContextHolder 获取到当前用户的信息。
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
