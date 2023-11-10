package com.lec.spring.di05;

import com.lec.spring.beans.MessageBean;
import org.springframework.stereotype.Component;

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











