package com.lec.spring.controller1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Random;
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

/*
     ViewResolver는 view 파일을 찾아낸다.  (view 파일을 template 이라고 한다)
     prefix + "home" + suffix =>           (prefix 기본값은 "templates/" 이다)
     "templates/views/" + "home" + ".html"  => "templates/views/home.html"

     View Resolver 가 선택한 '템플릿 파일'과 'Model 데이터'는
     설정된 Template Engine 에 넘겨진다 (ex: Thymeleaf, Mustache..)

     템플릿 엔진이 '템플릿파일' + '데이터'를 조립하여 response 할 데이터 생성 (렌더링)
     위 생성된 결과가 결국 response 된다.
*/

    @RequestMapping("/aaa") //  ← /aaa 로 들어온 request 처리
    public String home(Model model){
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(5);

        model.addAttribute("first", a);
        model.addAttribute("second", b);

        return "aaa/my";
        // prefix + "aaa/my" + suffix
        // => "templates/views/" + "aaa/my" + ".html"
        // => "templates/views/aaa/my.html"
    }

    // 핸들러 이름은 그닥 중요하진 않다.
    // ※ 그러나 가급적,
    //   'request url' - 'handler 이름' - 'view 이름'은
    //    같거나 동일한 맥락으로 일치시켜주는게 덜 헷갈리고 코드 가독성에 좋다.

    // ex)   /read  -> read() ->  read.html

    @RequestMapping("/aaa/bbb")
    public String aaabbb(Model model){
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(5);

        model.addAttribute("first", a);
        model.addAttribute("second", b);
        return "aaa/bbb/title";  // templates/views/aaa/bbb/title.html
    }

    // routing url 이 중복되면 '서버 가동시' 에러 발생
    //       IllegalStateException: Ambiguous mapping
//    @RequestMapping("/aaa")
//    public String aaa2(){
//        return "aaa";
//    }

    // void 를 리턴하는 handler
    // 기본적으로 request url 과 같은 view 를 response 한다.
    // (일반적으로 많이 사용)
    @RequestMapping("/aaa/bbb/ccc")
    public void aaabbbccc(){
        // templates/views/ + "aaa/bbb/ccc" + ".html"
    }

    // HttpServletRequest (aka. request객체)
    //    request 에 대한 정보는 HttpServletRequest 객체를 통해 읽을수 있다.
    //       (url, parameter, cookie, session, header...)
    //    이는 handler 의 매개변수로 설정해놓으면 request 시 spring container 가 전달해준다.




}  // enc Controller













