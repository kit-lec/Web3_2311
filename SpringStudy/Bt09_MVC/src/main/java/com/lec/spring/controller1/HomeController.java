package com.lec.spring.controller1;

import com.lec.spring.common.U;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

    @RequestMapping("/aaa/bbb/HttpServletRequest")
    public void httpServletRequest(Model model, HttpServletRequest request){
        String method = request.getMethod();  // "GET", "POST"....
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();


        model.addAttribute("method", method);
        model.addAttribute("scheme", scheme);
        model.addAttribute("serverName", serverName);
        model.addAttribute("serverPort", serverPort);
    }

    //------------------------------------------------------------
    // @ResponseBody
    //   자바 객체를 그대로 response 함 (view 를 사용하지 않음)
    //   String 을 리턴하면 문자열 텍스트로 response
    //   Java 객체를 리턴하면 JSON 으로 response
    //       List<>, Set<>, 배열 => JSON array 로 변환
    //       Map<> => JSON object 로 변환
    //       Java Object => JSON object 로 변환  (Property 사용!)
    //

    @RequestMapping("/ccc")
    @ResponseBody
    public String ccc(){
        return "ccc";
    }
    @RequestMapping("/ccc2")
    @ResponseBody
    public String ccc2(){
        StringBuffer buff = new StringBuffer();
        buff.append("<h1>Hello Spring</h1>");
        buff.append("<h3>spring boot</h3>");
        return buff.toString();
    }

    @RequestMapping("/ddd")
    @ResponseBody
    public List<Integer> ddd(){
        return List.of(10, 20, 30);
    }


    @RequestMapping("/eee")
    @ResponseBody
    public Map<String, Integer> eee(){
        return Map.of("국어", 100, "영어", 80, "수학", 73);
    }

    @Data
    @AllArgsConstructor
    class Product {
        private int no;
        private String name;
        private boolean sold;

        public long getAge(){
            return 345L;
        }
    }

    @RequestMapping("/fff")
    @ResponseBody
    public Product fff(){
        return new Product(10, "자전거", false);
    }

    //----------------------------------------------------------------------
    /**
     * 특정 request method 에 동작하는 handler 설정 가능
     *
     * @RequestMapping ← 모든 (혹은 특정) request method 에 대해서 동작
     *
     * @GetMapping, @PostMapping, @PutMapping .. ← 특정 request method 에 대해 동작
     *    단일 request method 에서만 동작하는 핸들러는 이를 사용하는게 간편하다
     */

    @GetMapping("/get")
    @ResponseBody
    public String get(HttpServletRequest request){
        return U.requestInfo(request);
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(HttpServletRequest request){
        return U.requestInfo(request);
    }

    @PutMapping("/put")
    @ResponseBody
    public String put(HttpServletRequest request){
        return U.requestInfo(request);
    }

    // Get, Post <- 두가지 request method 에 대해서만 동작시키기
    @RequestMapping(value = "/getpost", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getpost(HttpServletRequest request){
        return U.requestInfo(request);
    }

    // 동일 url 에 대해서도 서로 다른 request method 에 따라 '다른 동작' 을 수행할 경우
    @GetMapping("/action")
    @ResponseBody
    public String action1(HttpServletRequest request){
        return U.requestInfo(request);
    }

    @PostMapping("/action") @ResponseBody
    public String action2(HttpServletRequest request){
        return U.requestInfo(request);
    }
    @PutMapping("/action") @ResponseBody
    public String action3(HttpServletRequest request){
        return U.requestInfo(request);
    }
    @DeleteMapping("/action") @ResponseBody
    public String action4(HttpServletRequest request){
        return U.requestInfo(request);
    }

    // 서로 다른 url 에 대해서도 동일 handler 동작 가능
    @RequestMapping(value = {"/act1", "/aaa/act2", "/aaa/bbb/act3"})
    @ResponseBody
    public String act(HttpServletRequest request) {
        return U.requestInfo(request);
    }







}  // enc Controller













