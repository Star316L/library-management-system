package com.example.demo.common;

import lombok.Data;

/**
 * ClassName: Result
 * Package: com.booksystem.common
 * Description:
 *
 * @Author 王顺亮
 * @Create 2026/1/17 13:21
 * @Version 1.0
 */
@Data
public class Result<T>{
    private Integer code;    // 状态码
    private String message;  // 消息
    private T data;          // 数据
    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // 失败
    public static <T> Result<T> error(String message) {
        return error(400, message);
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
