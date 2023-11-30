package com.lec.spring.controller6;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lec.spring.domain.User;
import com.lec.spring.domain.dto.ResultDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RtController3 {

    RestTemplate rt = new RestTemplate();

    String[] jsonArr = {
            // [0]
            """
        {
            "status": "SUCCESS",
            "code": 34200,
            "response_time": "2023-07-03 13:09:06"
        }
        """,
            // [1]
            """
        {
            "status": "SUCCESS",
            "code": 34200.6,
            "response_time": "2023-07-03 13:09:06"            
        }
        """,
            // [2]
            """
        {
            "status": "SUCCESS",
            "code": 34200,
            "response_time": "2023-07-03 13:09:06",
            "points": [100, 200, 300],
            "ages": [12, 34, 75, 19]            
        }
        """,
            // [3]
            """
        {
            "scores": {
                "kor": 100,
                "eng": 34,
                "math": 23
            },
            "colors": {
                "beige": "#F5F5DC",
                "cyan": "#00FFFF"
            }
        }
        """,
            // [4]
            """
        {
            "status": "SUCCESS",
            "code": 34200,
            "response_time": "2023-07-03 13:09:06",
            "result": [
                {
                    "name": "John",
                    "age": 34
                },
                {
                    "name": "Susan",
                    "age": 78
                }             
            ]                        
        }
        """,
            // [5]
            """
        {
            "status": "SUCCESS",
            "code": 34200,
            "response_time": "2023-07-03 13:09:06",
            "result": {
                "red": 100, 
                "blue": 60,
                "green": 23
            }                        
        }
        """,
            // [6]
            """
        {
            "status": "SUCCESS",
            "code": 34200,
            "response_time": "2023-07-03 13:09:06",
            "result": {
                "name": "John",
                "age": 34              
            }                        
        }
        """,
    };

    @RequestMapping("/server/{n}")
    public ResponseEntity<String> server01(@PathVariable Integer n){
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArr[n]);
    }

    //-------------------------------------------------------
    @RequestMapping("/mapTest1")
    public String mapTest1(){
        // url 은 반드시 '절대 경로' 이어야 한다.
        String url = "http://localhost:8080/server/0";
        var result = rt.getForObject(url, Data1.class);
        return result.toString();
    }

    @Data @NoArgsConstructor
    static class Data1 {
        String status;
        Integer code;
        String response_time;
    }

    //-------------------------------------------------------
    @RequestMapping("/mapTest2")
    public String mapTest2(){
        String url = "http://localhost:8080/server/0";
        var result = rt.getForObject(url, Data2.class);
        return result.toString();
    }

    @Data @NoArgsConstructor
    static class Data2 {
        String status;
        Integer code;
        @JsonProperty("response_time")
        String responseTime;
    }

    //-------------------------------------------------------
    // java.time.* 객체로 받아내기
    @RequestMapping("/mapTest3")
    public String mapTest3(){
        String url = "http://localhost:8080/server/1";
        var result = rt.getForObject(url, Data3.class);
        return result.toString();
    }

    @Data @NoArgsConstructor
    static class Data3 {
        String status;
        Double code;
        @JsonProperty("response_time")
        @JsonDeserialize(using= LocalDateTimeDeserializer.class)
        @JsonSerialize(using= LocalDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime responseTime;
    }

    //-------------------------------------------------------
    // ● 상속받은 객체
    // ● JSON 배열은 Java List 나 Array 로 받을수 있다.
    @RequestMapping("/mapTest4")
    public String mapTest4(){
        String url = "http://localhost:8080/server/2";
        var result = rt.getForObject(url, Data4.class);
        return result.toString();
    }

    @Data @NoArgsConstructor
    @ToString(callSuper = true)
    static class Data4 extends Data3 {
        @JsonProperty("points")
        List<Integer> pointList;
        @JsonProperty("ages")
        int[] ageArr;
    }

    //----------------------------------------------
    // ● JSON Object 는
    //     1. Java Map<k, v> 으로 받을수 있다.
    //     2. Java Object 로 받을수도 있다
    @RequestMapping("/mapTest5")
    public String mapTest5() {
        String url = "http://localhost:8080/server/3";

        List<String> output = new ArrayList<>();

        {
            var result = rt.getForObject(url, Data5.class);
            output.add(result.getColors().get("beige"));
            output.add(result.getScores().get("kor").toString());
            output.add(result.toString());
        }
        output.add("<br>");
        {
            var result = rt.getForObject(url, Data6.class);
            output.add(result.getColors().getBeige());
            output.add(result.getScores().getKor().toString());
            output.add(result.toString());
        }


        return output.stream()
                .collect(Collectors.joining("<br>"));
    }

    @Data
    static class Data5 {
        Map<String, Integer> scores;
        Map<String, String> colors;
    }

    @Data
    static class Data6 {

        Score scores;
        Color colors;

        @Data
        static class Score {
            Integer kor, eng, math;
        }

        @Data
        static class Color {
            String beige, cyan;
        }
    }

    //----------------------------------------------
    // Json Object 의 배열 => Java object 의 List (혹은 배열)
    @RequestMapping("/mapTest7")
    public String mapTest7(){
        String url = "http://localhost:8080/server/4";
        var result = rt.getForObject(url, Data7.class);

        //result.getResult()

        return result.toString();
    }

    @Data
    static class Data7 extends Data3 {
        List<User> result;

        // 아래와 같이 해도 가능.
//        @JsonProperty("result")
//        User[] result2;

        //List<Map<String, Object>> result;

        //Map<String, Object> [] result;
    }

    //--------------------------------------------------------------
    // API 서비스(들) 에서 response 되는 JSON 을 설계하면서
    // 공통적인 property 들도 있지만
    // 결과 데이터의 타입은 다른 경우가 있다.
    // 이 경우 매핑할 java class 를 generic 타입으로 정의해볼수도 있다.

    @RequestMapping("/mapTest8")
    public String mapTest8(){
        List<String> output = new ArrayList<>();

        {
            String url = "http://localhost:8080/server/5";
            Data8<RGBColor> result = rt.getForObject(url, Data8.class);
            output.add(result.toString());
        }

        {
            String url = "http://localhost:8080/server/6";
            Data8<User> result = rt.getForObject(url, Data8.class);
            output.add(result.toString());
        }

        return output.stream()
                .collect(Collectors.joining("<br>"));

    }

    @Data
    static class Data8<T> extends Data3 {
        T result;
    }

    @Data
    static class RGBColor {
        Integer red, blue, green;
    }


    //--------------------------------------------------------------
    // json 을 매핑하고자 하는 Java Pojo 클래스 를 정의하기
    // https://www.jsonschema2pojo.org/
    // [4] 를 위 사이트에서 만들기 => ResultDTO 객체 생성
    @RequestMapping("/mapTest9")
    public String mapTest9(){
        String url = "http://localhost:8080/server/4";
        var resultDto = rt.getForObject(url, ResultDTO.class);

        StringBuffer sb = new StringBuffer();
        sb.append(resultDto.getStatus() + "<br>");
        sb.append(resultDto.getCode() + "<br>");
        sb.append(resultDto.getResponseTime() + "<br>");

        resultDto.getResult().forEach(e ->
                sb.append(" - " + e + "<br>"));

        return sb.toString();
    }

}










