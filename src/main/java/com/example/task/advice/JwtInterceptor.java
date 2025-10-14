package com.example.task.advice;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.task.common.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * JWT拦截器，用于验证请求中的JWT令牌
 * @author 陈高明
 * @since 2025-10-11
 */
    
public class JwtInterceptor implements HandlerInterceptor {
    // 这里可以添加JWT验证的逻辑
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        // 示例：检查请求头中的Authorization字段
        String token=request.getHeader("Authorization").replace("Bearer ","");
        Long userId=JwtUtils.parseToken(token);
        request.setAttribute("userId", userId);
        return true; // 如果验证通过，返回true，继续处理请求
    }
}
