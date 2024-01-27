package com.example.ourhome.webProject.webSecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 웹 보안 활성화
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    //@Bean
    //public static BCryptPasswordEncoder bCryptPasswordEncoder {
        //return new BCryptPasswordEncoder();
    //}

}
