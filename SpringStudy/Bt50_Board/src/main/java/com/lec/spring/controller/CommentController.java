package com.lec.spring.controller;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   // 데이터를 response <= @Contoller + @ResponseBody
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/test1")
    public QryCommentList test1(Long id){

        var comments = commentService.list(1L);
        QryCommentList list = new QryCommentList();

        return list;
    }


}













