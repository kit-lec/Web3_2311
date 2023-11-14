package com.lec.spring.controller3;

import com.lec.spring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    //--------------------------------------------------------
    // 데이터 준비
    String [] arr1 = new String[]{"AAA", "BBB", "CCC", "DDD", "EEE"};
    Member[] arr2;
    List<Integer> list1 = new ArrayList<>();
    List<Member> list2 = new ArrayList<>();

    Map<Integer, String> map1 = new HashMap<>();
    Map<String, Member> map2 = new HashMap<>();


    public ThymeleafController(){
        System.out.println(getClass().getName() + "() 생성");

        Member member;
        // 데이터 초기화
        for(int i = 0; i < 5; i++) {
            list1.add(i * 1000);
            member = new Member(100 + i, "u0" + i, "p0" + i, arr1[i],
                    LocalDateTime.now());
            list2.add(member);
            map1.put(i, arr1[i]);
            map2.put(arr1[i], member);
        }
        arr2 = list2.toArray(new Member[5]);

        // 확인해보기
        System.out.println("arr1: " + Arrays.toString(arr1));
        System.out.println("arr2: " + Arrays.toString(arr2));

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

        System.out.println("map1: " + map1);
        System.out.println("map2: " + map2);
    }

    @RequestMapping("/sample1")
    public void sample1(Model model){
        model.addAttribute("greeting", "Hello Thymeleaf");
        model.addAttribute("date", LocalDateTime.now());
    }

    /*********************************************************
     * Standard Expression Syntax
     *      - Literals
     *      - Variable Expressions: ${...}
     */
    @RequestMapping("/sample2")
    public void sample2(Model model){
        Member member = new Member(123, "u00", "p00", "홍길동", LocalDateTime.now());

        model.addAttribute("m", member);

        System.out.println(model.getAttribute("m"));
        System.out.println(model.getAttribute("aaa"));  // 없는 attribute 꺼내려 하면 null 리턴

        System.out.println(model.addAttribute("arr1", arr1));
        System.out.println(model.addAttribute("arr2", arr2));
        System.out.println(model.addAttribute("list1", list1));
        System.out.println(model.addAttribute("list2", list2));
        System.out.println(model.addAttribute("map1", map1));
        System.out.println(model.addAttribute("map2", map2));

    }

}





