package com.itheima.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

public class BCryptPasswordEncoderUtils {

    @Resource(name = "passwordEncoder")
    private static BCryptPasswordEncoder encoder;

    public static String encodPassword(String password){
        return encoder.encode(password);
    }
}
