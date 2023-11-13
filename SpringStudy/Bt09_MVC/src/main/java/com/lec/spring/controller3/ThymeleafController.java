package com.lec.spring.controller3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    // TODO

    public ThymeleafController(){
        System.out.println(getClass().getName() + "() 생성");
    }

}





