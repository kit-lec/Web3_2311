package com.lec.spring.di04;

import com.lec.spring.beans.MessageBean;
import org.springframework.stereotype.Component;

@Component("ENG")  // 이름이 "ENG" 인 MessageBean 타입 bean 생성
public class MessageEng implements MessageBean {

    String msg = "Good Morning";

    // 생성자 : 언제 생성되는지 주목!
    public MessageEng(){
        System.out.println(getClass().getName() + "() 생성");
    }

    @Override
    public void sayHello() {
        System.out.println(msg);
    }
}











