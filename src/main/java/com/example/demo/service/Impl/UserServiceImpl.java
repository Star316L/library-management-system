package com.example.demo.service.Impl;





import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResultDTO;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RegisterResultDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.AuthMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserServiceImpl
 * Package: com.booksystem.service.Impl
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/17 12:03
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<AuthMapper, User> implements UserService {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 密码加密器
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResultDTO login(LoginRequest loginRequest) {
        // 1. 参数校验（虽然DTO有注解，但这里再做一次）
        if (!StringUtils.hasText(loginRequest.getUsername()) ||
                !StringUtils.hasText(loginRequest.getPassword())) {
            throw new RuntimeException("用户名或密码不能为空");
        }
        // 2. 查询用户
        User user = getByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 3. 检查账号状态
        if (user.getStatus() != 1) {
            throw new RuntimeException("账号已被禁用");
        }

        // 4. 验证密码
// 4. 验证密码（使用BCrypt加密验证）
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.warn("登录失败：密码错误 - {}", loginRequest.getUsername());
            throw new RuntimeException("用户名或密码错误");
        }

        // 5. 更新最后登录时间
        authMapper.updateById(user);
        // 6. 生成Token（这里先模拟，实际要用JWT）
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole());
        // 7. 构造返回结果
        LoginResultDTO result = new LoginResultDTO();
        BeanUtils.copyProperties(user, result);
        result.setToken(token);
        System.out.println(user);
        log.info("用户 {} 登录成功", loginRequest.getUsername());
        return result;
    }
    public User getByUsername(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        List<User> users = authMapper.selectByMap(params);
        System.out.println(users);
        if (users == null) {
            System.out.println("用户不存在或已被删除");
            return null;
        }
        System.out.println("查询到的用户名: "+users.getFirst().getPassword());
        return users.getFirst();

    }

    @Override
    public RegisterResultDTO register(RegisterRequest request) {
        // 1. 校验确认密码
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("两次输入的密码不一致");
        }
        // 2. 校验密码复杂度（示例：需包含数字和字母）
        if (!isPasswordStrong(request.getPassword())) {
            throw new IllegalArgumentException("密码必须至少6位，且包含大小写字母和数字");
        }
        // 3. 检查用户名和邮箱唯一性
        if (authMapper.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        System.out.println("assa");
        // 检查邮箱是否已存在
        if (authMapper.existsByEmail(request.getEmail())) {
            throw  new IllegalArgumentException("邮箱已注册");
        }
        // 4. 加密密码
        User user = new User();
        user.setUsername(request.getUsername());
        // 加密密码
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus(1);
        user.setRole("user");
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        // 6. 插入用户到数据库
        int rows = authMapper.insert(user);
        System.out.println(rows);
        if (rows <= 0) {
            throw new RuntimeException("用户注册失败，请稍后重试");
        }
        authMapper.updateById(user);
        System.out.println(user);
        // 6. 生成Token（这里先模拟，实际要用JWT）
        String token = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole());
        // 7. 构造返回结果
        RegisterResultDTO result = new RegisterResultDTO();
        BeanUtils.copyProperties(user, result);
        result.setToken(token);
        return result;
    }
    private boolean isPasswordStrong(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,12}$";
        return password.matches(passwordPattern);
    }
}
