package com.shop.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String str = new BCryptPasswordEncoder().encode("123456");
        System.out.println(str);
        String str1 = new BCryptPasswordEncoder().encode("123456");
        System.out.println(str1);
        System.out.println(b.matches(str,str1));
        System.out.println(b.matches("123456",str1));
        System.out.println(b.matches("123456",str));
    }
}
