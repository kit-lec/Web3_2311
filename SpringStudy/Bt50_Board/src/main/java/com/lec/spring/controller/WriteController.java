package com.lec.spring.controller;

import com.lec.spring.domain.Write;
import com.lec.spring.domain.WriteValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WriteController {

    @RequestMapping("/write")
    public void write(){}

    @RequestMapping("/writeOk")
    @ResponseBody
    public String writeOk(
            @Valid Write write
            , BindingResult result
            ){
        System.out.println(write);
//        System.out.println("에러개수: " + result.getErrorCount());  // 바인딩 과정에 발생된 에러의 개수

        showErrors(result);

        return """
                id: %d<br>
                작성자: %s<br>
                제목: %s<br>
                <button onclick="history.back()">뒤로가기</button>
                """.formatted(write.getId(), write.getName(), write.getSubject());
    }

    // 바인딩 에러 출력 도우미 메소드
    public void showErrors(Errors errors){
        if(errors.hasErrors()){
            System.out.println("에러개수: " + errors.getErrorCount());
            System.out.println("\t[field]\t|[code]");
            List<FieldError> errList = errors.getFieldErrors();
            for(FieldError err : errList){
                System.out.println("\t" + err.getField() + "\t|" + err.getCode());
            }
        } else {
            System.out.println("에러 없슴");
        }
    }

    // 이 컨트롤러 클래스의 handler 에서  폼 데이터 를 바인딩할때 검증하는 Validator 객체 지정
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new WriteValidator());
    }

}














