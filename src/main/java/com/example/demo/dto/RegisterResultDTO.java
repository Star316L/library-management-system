package com.example.demo.dto;

import lombok.Data;

/**
 * ClassName: RegisterResultDTO
 * Package: com.example.demo.dto
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/25 23:10
 * @Version 1.0
 */
@Data
public class RegisterResultDTO {
    private Long Id;
    private String username;
    private String phone;
    private String role;
    private String token;
}
