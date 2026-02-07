package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResultDTO;
import com.example.demo.common.Result;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResultDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.example.demo
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/18 21:32
 * @Version 1.0
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许所有来源
@RequestMapping("/api/auth")
@Api(tags = "认证管理")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test2")
    public String test2() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return "✅ MySQL驱动加载成功！";
        } catch (ClassNotFoundException e) {
            return "❌ MySQL驱动加载失败：" + e.getMessage();
        }
    }
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<LoginResultDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            LoginResultDTO result = userService.login(loginRequest);
            return Result.success("登录成功", result);
        } catch (RuntimeException e) {
            log.error("登录失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("登录异常", e);
            return Result.error("系统异常，请稍后重试");
        }
    }
    @PostMapping("/register")
    @ApiOperation("用户登录")
    public Result<RegisterResultDTO> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            RegisterResultDTO result = userService.register(registerRequest);
            return Result.success("注册成功", result);
        } catch (RuntimeException e) {
            log.error("登录失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("登录异常", e);
            return Result.error("系统异常，请稍后重试");
        }
    }
}
