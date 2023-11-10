package com.lec.spring.di06;
/*
 * stereotype
 *   컨테이너에 bean으로 생성하는 annotation 들
 *
 * @Component       <-- 일반적인 bean
 *  └ ＠Controller   <-- Spring MVC 에서 Controller 로 사용
 *  └ ＠Service      <-- Service 단으로 사용
 *  └ ＠Repository   <-- Repository 단. DAO, Persistence 로 사용
 */

import com.lec.spring.di05.DIMain05;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class DIMain06 {
    public static void main(String[] args) {
        SpringApplication.run(DIMain06.class, args);
    }
}

@Controller
class UserController{

    @Autowired
    private UserService userService;

    public UserController(){
        System.out.println("UserController() 생성");
    }
}

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(){
        System.out.println("UserService() 생성");
    }
}

@Repository
class UserRepository {

    // DI 설정할때 순환참조 발생하지 않도록 주의!
    //    @Autowired
//    UserController userController;

    public UserRepository(){
        System.out.println("UserRepository() 생성");
    }
}

/* 순환참조 발생하지 않도록 주의

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  userController (field private com.lec.spring.di06.UserService com.lec.spring.di06.UserController.userService)
↑     ↓
|  userService (field private com.lec.spring.di06.UserRepository com.lec.spring.di06.UserService.userRepository)
↑     ↓
|  userRepository (field com.lec.spring.di06.UserController com.lec.spring.di06.UserRepository.userController)
└─────┘

 */






