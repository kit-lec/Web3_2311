package com.lec.spring.controller4;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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





}
















