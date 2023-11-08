package com.lec.spring.di02;

import com.lec.spring.beans.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DIMain02 implements CommandLineRunner {

    // 의존자동주입. 컨테이너에 동일 타입의 bean 객체가 있으면 주입
    public MessageBean msg;   // 필요로 하는 (의존하는) 객체

    @Autowired  // setter injection
    public void setMsg(MessageBean msg) {
        System.out.println("setMsg() 호출");
        this.msg = msg;
    }

    @Autowired  // field injection
    ApplicationContext ctx; // 스프링 컨테이너, 컨텍스트, IoC 컨테이너, Bean Factory 등 지칭하는 용어 다양

    public DIMain02(){
        System.out.println(getClass().getName() + "() 생성");
    }

    public static void main(String[] args) {
        System.out.println("Main 시작");

        SpringApplication.run(DIMain02.class, args);

        System.out.println("Main 종료");
    } //  end main()


    @Override
    public void run(String... args) throws Exception {
        // 컨테이너 생성직후 실행
        System.out.println("컨테이너 생성 완료후 실행");
        msg.sayHello();

        System.out.println("생성된 bean 의 개수: " + ctx.getBeanDefinitionCount());

        // 모든 bean 에는 고유한 name(id) 이 부여된다.
        for(var name : ctx.getBeanDefinitionNames()){
            System.out.println(name + " : " + ctx.getBean(name));
        }
    }
} // end class







