package com.example.demo.interceptor;

import com.example.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: JwtInterceptor
 * Package: com.example.demo.interceptor
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/20 22:45
 * @Version 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        // 如果token为空，或者不是以"Bearer "开头，则返回错误
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is missing or invalid");
            return false;
        }
        // 提取token，去掉"Bearer "前缀
        String actualToken = token.substring(7);
        try {
            // 验证token，并从中获取用户名
            String username = jwtUtil.getUsernameFromToken(actualToken);
            // 将用户名存入request，方便后续使用
            request.setAttribute("username", username);
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is invalid or expired");
            return false;
        }
    }
}
