package com.example.demo.entity;



import lombok.Data;

import java.util.Date;

/**
 * ClassName: User
 * Package: com.booksystem.entity
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/14 20:08
 * @Version 1.0
 */
@Data
public class User {
    private Long Id; //用户id
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String email;
    private Integer status;
    private String role;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;
    private Integer deleted; // 逻辑删除
}
