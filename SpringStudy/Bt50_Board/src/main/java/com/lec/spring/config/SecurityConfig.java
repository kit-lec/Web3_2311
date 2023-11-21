package com.lec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // PasswordEncoder 를 bean 으로 IoC 에 등록
    // IoC 에 등록된다, IoC 내에선 '어디서든' 가져다가 사용할수 있다.
    @Bean
    public PasswordEncoder encoder(){
        System.out.println("PasswordEncoder bean 생성");
        return new BCryptPasswordEncoder();
    }


    // ↓ Security 를 동작 시키지 않기
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().anyRequest();   // 어떠한 request 도 Security 가 무시함.
    }


}
