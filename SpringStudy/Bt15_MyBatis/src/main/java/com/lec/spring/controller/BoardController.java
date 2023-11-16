package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller layer
//  request 처리 ~ response
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/write")   // /board/write
    public void write(){}

    @GetMapping("/detail")
    public void detail(){}

    @GetMapping("/list")
    public void list(){}

    @GetMapping("/update")
    public void update(){}

}
























