package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResultDTO;
import com.example.demo.entity.User;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResultDTO;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Package: com.booksystem.service
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/17 12:02
 * @Version 1.0
 */
@Service
public interface UserService extends IService<User> {

    /**
     * 用户登录
     */
    LoginResultDTO login(LoginRequest loginRequest);
    /**
     * 根据用户名查询用户
     */
    User getByUsername(String username);
    /**
     * 用户注册
     */
    RegisterResultDTO register(RegisterRequest registerRequest);
}