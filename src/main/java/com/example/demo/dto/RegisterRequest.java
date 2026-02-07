package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * ClassName: RegisterRequest
 * Package: com.example.demo.dto
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/25 22:40
 * @Version 1.0
 */
@Data
public class RegisterRequest {

    @NotBlank(message = "请输入用户名")
    @Size(min = 3, max = 20, message = "用户名长度为3-20位")
    private String username;

    @NotBlank(message = "请输入手机号")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确的11位手机号")
    private String phone;

    @NotBlank(message = "请输入邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过50个字符")
    private String email;
    @NotBlank(message = "请设置密码")
    @Size(min = 6, message = "密码至少6个字符")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,12}$",
            message = "密码需包含大小写字母和数字"
    )
    private String password;

    // 确认密码字段，用于两次密码输入一致性验证
    // 注意：这个字段需要在前端也进行验证，确保两次输入一致
    @NotBlank(message = "请确认密码")
    private String confirmPassword;
}
