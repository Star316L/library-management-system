package com.example.demo.utils;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGenerator {

    /**
     * 生成安全的HS512密钥
     * 密钥长度至少512位（64字节）
     */
    public static String generateSecureKey() {
        // 使用Keys工具类生成安全的HS512密钥
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // 将密钥转换为Base64字符串，便于配置文件中使用
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("生成的JWT密钥(Base64): " + base64Key);
        System.out.println("密钥长度: " + base64Key.length() * 8 + " bits");

        return base64Key;
    }

    public static void main(String[] args) {
        // 生成并打印密钥
        String key = generateSecureKey();
    }
}
