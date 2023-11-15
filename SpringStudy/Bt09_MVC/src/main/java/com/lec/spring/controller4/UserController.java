package com.lec.spring.controller4;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/delete")
    @ResponseBody
    public String delMember(HttpServletRequest request, Model model){
        List<String> result = new ArrayList<>();
        String id = request.getParameter("id"); // "id" 란 name 의 parameter 값 리턴 (String)
        result.add("id = " + id);

        int age = Integer.parseInt(request.getParameter("age"));
        result.add("age = " + age);

        // 동일한 name 의 parameter가 여러개 인 경우
        // getParameterValues(name) => String[] 리턴
        String [] ages = request.getParameterValues("age");
        result.add("ages = " + Arrays.toString(ages));

        return result.stream().collect(Collectors.joining("<br>"));
    }

    @GetMapping("/regist")
    public void registMember(){}

    @RequestMapping("/regOk")
    @ResponseBody
    public String regOk(HttpServletRequest request){

        String result = request.getRequestURI() + " : " + request.getMethod() + "<br>";
        result += "username: " + request.getParameter("username") + "<br>";
        return result;
    }

    // 단일 request method 에서만 동작하는 핸들러는
    // @GetMapping(..), @PostMapping(..), @PutMapping(...)  등을 사용하는게 간편하다


    //-------------------------------------------------------------
    // parameter name 값과 동일한 이름의 매개변수를 핸들러에 지정해서 받아오기
    @RequestMapping("/find1")
    @ResponseBody
    public String find1(String name, String id){
        return "user/find1: id=" + id + ", name=" + name;
    }

    // 숫자 타입은 바로 parsing 하여 받을수 있다.
    @RequestMapping("/find2")
    @ResponseBody
    //public String find2(double id, String name){  // primitive 타입인데, parameter 에 없는 경우, 혹은 parsing 불가능하면 에러 발생
    public String find2(Double id, String name){ // wrapper 타입이면, parameter 없으면 null 값으로 받아옴. parsing 불가하면 에러
        return "user/find2: id=" + id + ", name=" + name;
    }

    // parameter 를 특정 타입의 자바객체로 변환하여 받아내는 것을 'binding' 한다고 한다
    // parameter "name"-"value"  -->  Java 변수, 객체


    // 동일한 name 의 parameter(들) --> '배열' 매개변수로 받을수 있다.
    @RequestMapping("/find3")
    @ResponseBody
    public String find3(int [] id, String [] name){
        return "user/find3: id=" + Arrays.toString(id) + ", name=" + Arrays.toString(name);
    }

    // 만약 request parameter 의 name 과 매개변수가 같을수 없는 상황이면
    // @RequestParam 애노테이션 사용
    @RequestMapping("/find4")
    @ResponseBody
    public String find4(
            @RequestParam(value = "id", required = false, defaultValue = "0") String userid  // "id" 란 name 의 parameter 값을 userid 매개변수에 binding 해준다
            , @RequestParam("name") String username
            ){
        return "user/find4: id=" + userid + ", name=" + username;
    }

    // 위의 경우 id 값이 없거나 변환 불가능하면 에러 발생한다.
    // (왜냐하면 @RequestParam 의 required=true 이기 때문이다)
    // @RequestParam(value="test", required=false, defaultValue="0") 을 이용하면 가능하긴 하다.
    // 또한, @RequestParam 과 Map<name, value> 을 사용하면 된다.
    @RequestMapping("/find5")
    @ResponseBody
    public String find5(@RequestParam Map<String, String> map){
        return "user/find5: " + map;
    }

    //--------------------------------------------------------------
    // Java 객체로 받기 (aka. 커맨드객체 (command object)

    // 게시글 등록 form
    @GetMapping("/write")
    public void writeBoard() {}

    // 기존의 방식대로라면
    // 매 parameter 들을 매개변수화 하는 것은 힘들다.
    @PostMapping("/writeOk")
//    @ResponseBody
//    public String writeOkBoard(String name, String subject, String content){
//        String result = "name=" + name + ", subject=" + subject + ", content=" + content;
//        return result;
//    }

    // Java 객체에 바인딩될때
    // 내부적으로 '기본생성자로 생성' 한뒤
    // set property 를 사용하여 각 parameter 를 Java객체에 binding 합니다

    // 커맨드 객체는 기본적으로 '객체타입명'으로 Model attribute 추가 된다. (소문자로)
    // Model attribute name 을 바꿀경우 @ModelAttribute 로 지정
    public void writeOkBoard(@ModelAttribute("DTO") Post post){
        System.out.println("/writeOk: " + post);
    }

    //--------------------------------------------------------------
    /**
     * @RequestBody 가 핸들러의 파라미터에 붙으면
     *     HTTP요청의 본문(body)이 그대로 전달
     *     xml 이나 json 기반의 메시지를 사용하는 경우 유용.
     */
    // ※ 테스트는 Postman 등으로 진행

    @PostMapping("/test01")
    @ResponseBody
    public User userTest01(int age, String name){
        User user = new User(age, name);
        return user;
    }
    // parameter 없으면 에러 -> int 인 경우
    // POST 요청
    //  ① query string  ?age=10&name=Juni  OK
    //  ② x-www-form-urlencoded 방식  OK
    //  ③ form-data 방식  OK
    //  ④ raw-json  (불가!)  500에러

    @PostMapping("/test02")
    @ResponseBody
    public User userTest02(User user){return user;}
    //  ① query string  ?age=10&name=Juni2  // OK
    //  ② x-www-form-urlencoded 방식  // OK
    //  ③ form-data 방식  // OK
    //  ④ raw-json  => 모두 null 값 (binding 된게 없다!)

    @PostMapping("/test03")
    @ResponseBody
    public User userTest03(@RequestBody  User user){return user;}
    //  ① ?age=10&name=Juni (에러 400 Bad Request : Required request body is missin)
    //  ② x-www-form-urlencoded 방식 (에러 415) Unsupported Media Type Content-Type 'application/x-www-form-urlencoded;charset=UTF-8' is not supported
    //  ③ form-data 방식  (에러 415) Unsupported Media Type  Content-Type 'multipart/form-data  .. is not supported
    //  ④ raw-json  => OK

    // @RequestHeader(key) : header value 도 받아올수 있다.
    @PostMapping("/test04")
    @ResponseBody
    public String usetTest04(
            @RequestBody User user,
            @RequestHeader("x-auth") String auth, // 이 경우 x-auth 가 없으면 에러 (required=true 가 디폴트)
            @RequestHeader(value = "x-key", required = false, defaultValue = "UVUV") String key
    ){
        return """
                User: %s
                auth: %s
                key: %s
                """.formatted(user, auth, key);
    }

    //----------------------------------------------------------------------------
    /**
     * @PathVariable 사용
     *
     *  request url 을 통해 parameter 를 받는 방법은 다음 방법들이 있다
     *   /API_NAME?key1=val1   <-- query string 사용
     *   /API_NAME/{value1}    <-- path variable 사용
     */

    @RequestMapping("/writePath/{name}/{subject}/{k3}")
    @ResponseBody
    public String writePathBoard(
            @PathVariable String name,
            @PathVariable String subject,
            @PathVariable(name="k3") String content
    ){
        return """
                name: %s<br>
                subject: %s<br>
                content: %s<br>
                """.formatted(name, subject, content);
    }

    //-----------------------------------
    // redirect
    //  "redirect:{url}" 을 리턴 => redirect 를 response
    //   redirect 의 response code 는 3xx

    @RequestMapping("/ageInput")
    public void ageInput(){}

    @RequestMapping("/ageCheck")
    public String chkAge(int age
                , RedirectAttributes redirectAttributes){  // redirect 되는 request 에 담을 parameter 지정

        redirectAttributes.addAttribute("age", age);   // "age" 라는 parameter 지정. query string 에 담겨 간다.

        if(age < 19){
            return "redirect:/user/underAge";   // View 를 리턴하는게 아니라 redirect 된다!
        } else {
            return "redirect:/user/adult";
        }
    }

    @RequestMapping("/underAge")
    @ResponseBody
    public String pageUnderAge(int age){
        return """
                미성년자입니다<br>
                나이: %s 살, %s 년 뒤에 사용 가능<br>                
                """.formatted(age, 19 - age);
    }
    @RequestMapping("/adult")
    @ResponseBody
    public String pageAdult(int age){
        return """
                성인 입니다<br>  
                나이: %d살<br>              
                """.formatted(age);
    }

    //----------------------------------------------------
    // forward:
    @RequestMapping("/detail")
    public String memberDetail(){
        System.out.println("/user/detail 요청");
        return "forward:/user/notfound";
    }

    @RequestMapping("/notfound")
    @ResponseBody
    public String memberNotFound(){
        System.out.println("/user/notfound 요청");
        return "/user/notfound";
    }


}
















