package com.example.demo.config;

import com.example.demo.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.smartcourse_manager
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/16 22:35
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，除了登录和注册等公开接口
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")  // 拦截所有
                .excludePathPatterns("/api/auth/login")  // 放行登录接口
                .excludePathPatterns("/api/auth/register");  // 放行注册接口
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOrigins("*")  // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)  // 注意：如果允许凭证，则不能使用通配符*
                .maxAge(3600);
    }
}
