package com.lec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 스프링 기본 Listener 작동
public class Bt65JpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bt65JpaBoardApplication.class, args);
	}

}
