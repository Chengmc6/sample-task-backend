package com.example.task.common;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * JWT工具类，用于生成和解析JWT
 * @author gaoming
 *
 */
public class JwtUtils {

    // 这个密钥应该足够长，以确保安全性
    private static final String SECRET_KEY = "gaoming-task-backend-jwt-secret-key-2025";
    //KEY 是从你的字符串密钥 SECRET_KEY 生成的加密密钥对象，用于安全地签发和验证 JWT。
    private static final Key KEY=Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // 生成JWT，包含用户ID，设置过期时间为1天
    public static String getToken(Long userId,String username){
        // 使用HS256算法生成JWT
        return Jwts.builder()
                .setSubject(String.valueOf(userId))//以用户id作为token主题
                .claim("username", username)//可以加入想要加入的字段，供后续使用
                .setIssuedAt(new Date())//token的签发日期
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1天后过期   
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();//打包token里的信息
    }
    // 解析JWT并提取用户ID
    public static Long parseToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }
    // 从HTTP请求的Authorization头中提取JWT
    public static String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
        }
    return null;
    }

    // 解析JWT并提取所有Claims
    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    // 从JWT中提取用户ID
    public static Long getUserId(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }

    // 从JWT中提取用户名
    public static String getUsername(String token) {
        return getClaims(token).get("username", String.class);

    }
    // 检查JWT是否过期
    public static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
