package com.example.edu_connect.Shared;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodeGenerator {
    public static String generatorCode(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = key.getBytes();
            byte[] hashBytes = md.digest(keyBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            String code = hexString.toString().substring(0, 8);
            return code;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
