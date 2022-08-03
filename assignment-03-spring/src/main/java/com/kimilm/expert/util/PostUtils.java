package com.kimilm.expert.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PostUtils {
    public final static String DATA = "data";
    public final static String MESSAGE = "msg";
    public final static String ACCESS_TOKEN = "Access-Token";
    public final static String REFRESH_TOKEN = "Refresh-Token";
    public final static String AUTHORIZATION = "Authorization";

    public static String encoding(String password) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        msg.update(password.getBytes(StandardCharsets.UTF_8));
        return new String(msg.digest(), StandardCharsets.UTF_8);
    }
}
