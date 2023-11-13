package com.lec.spring.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @RequestMapping("/list")   // request -->  /board/list
    public void listBoard(){
    }

    @RequestMapping("/write")
    public void writeBoard() {
    }

    @RequestMapping("/detail")  // request --> /board/detail
    public void detailBoard() {
    }

    @RequestMapping("/update")
    public void updateBoard() {
    }

    @RequestMapping("/delete")
    public void deleteBoard() {
    }

}
