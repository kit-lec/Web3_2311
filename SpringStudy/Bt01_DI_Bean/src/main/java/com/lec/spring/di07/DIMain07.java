package com.lec.spring.di07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

/**
 * @Value
 *   org.springframework.beans.factory.annotation.Value  (Lombok 의 Value 와 헷갈리지 말자)
 *
 *   스프링 빈 의 '필드' 등에 '값' 을 주입하는데 사용
 *   주입대상: 필드, 생성자, 메소드 매개변수
 *
 *   참조:
 *      https://www.baeldung.com/spring-value-annotation
 *
 *
 */

@SpringBootApplication
public class DIMain07 implements CommandLineRunner {
    @Value("${value.from.file}")
    private String value1;

    @Value("${app.server.port}")
    private int port1;

    // 없는 property 는 에러!
    @Value("${app.port:100}")  // default 값을 줄수 있다
    private int port2;

    @Value("${app.values1}")
    private String[] arr1;
    @Value("${app.values1}")
    private List<String> list1;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("value1: " + value1);
        System.out.println("port1: " + port1);
        System.out.println("port2: " + port2);
        System.out.println("arr1: " + Arrays.toString(arr1));
        System.out.println("list1: " + list1);
    }

    public static void main(String[] args) {
        SpringApplication.run(DIMain07.class, args);
    }
}












