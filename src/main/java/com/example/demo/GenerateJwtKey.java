package com.example.demo;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class GenerateJwtKey {
    public static void main(String[] args) {
        // 方法1: 使用Keys工具类生成
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("=== 安全的JWT密钥 (HS512) ===");
        System.out.println("Base64编码: " + base64Key);
        System.out.println("密钥长度: " + (base64Key.length() * 8) + " bits");
        System.out.println("原始字节长度: " + key.getEncoded().length + " bytes");
        System.out.println("算法: " + key.getAlgorithm());

        // 方法2: 生成随机长字符串
        System.out.println("\n=== 备用方案：长字符串密钥 ===");
        String longStringKey = generateRandomString(64); // 64字符，512位
        System.out.println("长字符串密钥: " + longStringKey);
        System.out.println("Base64编码: " + Base64.getEncoder().encodeToString(longStringKey.getBytes()));
    }

    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:,.<>?";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
