package com.lec.spring.di05;

import com.lec.spring.beans.MessageBean;
import com.lec.spring.beans.Score;
import com.lec.spring.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config05 {
    @Bean
    public Score scoreA() {
        return new Score(100, 80, 74, "좋아요");
    }

    @Bean(name = "Park")
    public Student stu1() {
        return new Student("박주희", 19, scoreA());
    }
    @Bean
    public Student stu2() {
        return new Student("홍길동", 25, scoreA());
    }

    @Bean
    @Primary
    public MessageBean msg1() {
        return new MessageEng();
    }
    @Bean
    public MessageBean msg2() {
        return new MessageKor();
    }


}









