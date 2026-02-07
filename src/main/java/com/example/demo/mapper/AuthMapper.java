package com.example.demo.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: AuthMapper
 * Package: com.example.demo.mapper
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/2/3 14:12
 * @Version 1.0
 */
@Mapper

public interface AuthMapper extends BaseMapper<User> {
    @Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
    boolean existsByEmail(String email);
    @Select("SELECT COUNT(*) FROM user WHERE username = #{username}")
    boolean existsByUsername(String username);
}
