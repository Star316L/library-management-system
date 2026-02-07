package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName: SecurityConfig
 * Package: com.example.demo.config
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/27 01:18
 * @Version 1.0
 */
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 可以指定强度，默认是10
        return new BCryptPasswordEncoder(12);
    }
}
