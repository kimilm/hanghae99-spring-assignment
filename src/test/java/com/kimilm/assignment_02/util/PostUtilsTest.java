package com.kimilm.assignment_02.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class PostUtilsTest {

    @Test
    void encoding() throws NoSuchAlgorithmException {
        String password1 = PostUtils.encoding("1234");
        String password2 = PostUtils.encoding("1234");
        String password3 = PostUtils.encoding("0123");
        Assertions.assertEquals(password1, password2);
        Assertions.assertNotEquals(password1, password3);
    }
}