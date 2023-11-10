package com.lec.spring.di04;

import com.lec.spring.beans.Score;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config04 {
    @Bean
    public Score score1() {
        return new Score(100, 80, 74, "좋아요");
    }
    @Bean(name="Kim") // "Kim" 이라는 이름의 Score 타입 빈 생성
    public Score score2() {
        return new Score(24, 42, 64, "나빠요");
    }
}









