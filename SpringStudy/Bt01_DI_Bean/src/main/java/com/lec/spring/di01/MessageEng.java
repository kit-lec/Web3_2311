package com.lec.spring.di01;

import com.lec.spring.beans.MessageBean;

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











