package com.lec.spring.controller;

import com.lec.spring.domain.Attachment;
import com.lec.spring.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController  // 데이터를 response <= @Contoller + @ResponseBody
// 파일 다운로드를 위해
public class AttachmentController {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Autowired
    private AttachmentService attachmentService;


    // 파일 다운로드
    // id: 첨부파일의 id
    // ResponseEntity<T> 를 사용하여
    // '직접' Response data 를 구성

    @RequestMapping("/board/download")
    public ResponseEntity<Object> download(Long id){

        if(id == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // 400

        Attachment file = attachmentService.findById(id);
        if(file == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // 404

        String sourceName = file.getSourcename();  // 원본 이름
        String fileName = file.getFilename();  // 저장된 파일명
        
        String path = new File(uploadDir, fileName).getAbsolutePath();  // 저장된 물리적인 경로

        try {
            // 파일 유형 (Mimetype) 추출
            String mimeType = Files.probeContentType(Paths.get(path));  // ex) "image/png"

            // 유형이 지정되지 않은 경유
            if(mimeType == null){
                mimeType = "application/octet-stream";  // 일련의 8bit 스트림 타입.  유형이 알려지지 않은 파일에 대한 형식 지정
            }

            // response body 준비
            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            // response header 세팅
            HttpHeaders headers = new HttpHeaders();
            // ↓ 원본 파일 이름(sourceName) 으로 다운로드 하게 하기위한 세팅
            headers.setContentDisposition(
                    ContentDisposition.builder("attachment").filename(URLEncoder.encode(sourceName, "utf-8")).build()
            );
            headers.setCacheControl("no-cache");
            headers.setContentType(MediaType.parseMediaType(mimeType));

            // ResponseEntity<> 리턴 (body, header, status)
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}











