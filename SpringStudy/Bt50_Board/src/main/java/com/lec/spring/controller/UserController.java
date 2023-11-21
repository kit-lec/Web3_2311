package com.lec.spring.controller;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login(Model model){}


    @PostMapping("/login")
    public void loginProcess(){
        System.out.println("/user/login [POST] 가 호출되나?");   // .loginProcessingUrl() 가 설정되어 있으면 호출안된다.
    }

}
