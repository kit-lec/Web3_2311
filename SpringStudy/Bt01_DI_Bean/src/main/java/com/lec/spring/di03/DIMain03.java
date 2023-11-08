package com.lec.spring.di03;

import com.lec.spring.beans.Score;
import com.lec.spring.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DIMain03 implements CommandLineRunner {

    @Autowired
    Score scoreA;

    @Autowired
    Student stuA;

    public static void main(String[] args) {
        System.out.println("Main  시작");
        SpringApplication.run(DIMain03.class, args);
        System.out.println("Main  종료");
    }

    @Autowired
    ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(scoreA);
        System.out.println(stuA);

        System.out.println(scoreA == stuA.getScore());

        // 컨테이너에 생성된 bean은 'bean 의 이름' 으로
        // bean 객체를 받아올수 있다.
        System.out.println("-".repeat(20));
        System.out.println(ctx.getBean("score1"));
        System.out.println(ctx.getBean("stu1"));
    }
}
