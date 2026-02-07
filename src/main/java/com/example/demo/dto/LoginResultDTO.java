package com.example.demo.dto;

import lombok.Data;

/**
 * ClassName: LoginResultDTO
 * Package: com.booksystem.dto
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/17 13:00
 * @Version 1.0
 */
@Data
public class LoginResultDTO {
    private Long Id;
    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String role;
    private String token;
}
