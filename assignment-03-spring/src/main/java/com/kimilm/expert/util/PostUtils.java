package com.kimilm.expert.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostUtils {
    public static String DATA = "data";
    public static String MESSAGE = "msg";
    public static String ACCESS_TOKEN = "Access-Token";
    public static String REFRESH_TOKEN = "Refresh-Token";

    public static String encoding(String password) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        msg.update(password.getBytes(StandardCharsets.UTF_8));
        return new String(msg.digest(), StandardCharsets.UTF_8);
    }
}
