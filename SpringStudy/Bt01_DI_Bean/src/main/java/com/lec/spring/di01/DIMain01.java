package com.lec.spring.di01;

import com.lec.spring.beans.MessageBean;

/*
 	Dependency Injection (DI, 의존주입)
 	필요한 객체는 누가 만들어 사용하나?

 	방법1 : 직접 생성하여 사용
 */
public class DIMain01 {

    public MessageBean msg;   // 필요로 하는 (의존하는) 객체

    public static void main(String[] args) {
        System.out.println("Main 시작");

        DIMain01 app = new DIMain01();
        app.test();

        System.out.println("Main 종료");
    } //  end main()

    private void test() {

        // 필요한 MessageBean 객체를 
        //msg = new MessageKor();  // 직접 만들어(new) 사용

        // 나중에 의존객체의 변경이 발생하면?
        msg = new MessageEng();
        // ↑ 결국 의존하는 측의 코드 수정이 발생해야만 한다.
        msg.sayHello();
    }

} // end class







