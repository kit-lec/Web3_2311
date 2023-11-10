package com.lec.spring.controller1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;

import java.time.LocalDateTime;
/*
     Controller
      - '어떠한 request' 를 받아서,
      - '어떠한 비스니즈 로직 (business logic)' 을 처리하고 하고 => 주로 Service 단에 위임
      - '어떠한 response' 를 할지를 정의
*/

@Controller
public class HomeController {

    public HomeController(){
        System.out.println(getClass().getName() + "() 생성");
    }

    /*
    '어떠한 url' 의 request 처리를
    '어떠한 controller' 의 '어떤 handler' 에게 전달하는 것을
    'Routing' 이라 한다!   (웹 프레임워크의 가장 기본적인 기능)
        url => controller => handler

    ※ Routing, 혹은 Request Mapping, 혹은 Url Mapping 이라고도 불린다.
*/
/*
     Controller 클래스 안에는 request를 처리(handle) 하는 메소드들을 정의한다
     이러한 메소드들을 'handler' 라 한다.
     handler 는
      - '어떠한 request' 를 받아서,
      - '어떠한 비스니즈 로직 (business logic)' 을 처리하고 하고
      - '어떠한 response' 를 할지를 정의
*/

    // 참고 RequestMapping
    //   https://www.baeldung.com/spring-requestmapping
    //   https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html

    @RequestMapping("/") //  "/" 라는 url 로 request 가 들어오면 아래 메소드가 처리(handle) 한다
    //@ResponseBody   // 리턴하는 값을 그.대.로 response
    public String now(Model model){ // Model: 데이터를 실어나르는 객체, View 로 전달됨!
        System.out.println("now() 호출됨");
        LocalDateTime t = LocalDateTime.now();

        // ↓ Model 에 데이터 담기. addAttribute(name, value)
        //  name 은 String,  value 는 Object
        model.addAttribute("serverTime", t);
        model.addAttribute("name", "홍길동");
        model.addAttribute("age", 29);


        // String 을 리턴. => View 이름 -> ViewResolver 를 통해 템플릿 파일을 찾아서 template engine에 전달
        return "home";
    }

}  // enc Controller







