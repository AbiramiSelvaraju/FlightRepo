package com.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {

    public static void main(String args[]){
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordEncoded = encoder.encode("flyhigh");

        System.out.println(passwordEncoded);
    }

}
