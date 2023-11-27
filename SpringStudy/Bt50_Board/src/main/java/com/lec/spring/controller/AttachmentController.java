package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 데이터를 response <= @Contoller + @ResponseBody
// 파일 다운로드를 위해
public class AttachmentController {

    @Value("${app.upload.path}")
    private String uploadDir;


    // 파일 다운로드
    // id: 첨부파일의 id
    // ResponseEntity<T> 를 사용하여
    // '직접' Response data 를 구성

    public ResponseEntity<Object> download(Long id){
        // TODO
        return null;
    }


}












