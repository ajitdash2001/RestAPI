package com.app.ems.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodeUtil {



    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("Sourabh123");
        System.out.println(encode);
    }
}
