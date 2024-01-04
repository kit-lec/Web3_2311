package com.lec.spring.listener;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserHistory;
import com.lec.spring.repository.UserHistoryRepository;
import com.lec.spring.support.BeanUtils;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class UserEntityListener {

//    @Autowired
//    private UserHistoryRepository userHistoryRepository;  // Entity Listener 는 Spring Bean 을 주입받지 못한다!

    @PostUpdate  // User 가 UPDATE 를 수행한 후
    @PostPersist  // User 가  INSERT 를 수행한 후
    public void addHistory(Object o){
        System.out.println(">> UserEntityListener#addHistory()");

        // Listener 에서 스프링 빈 객체 주입받기
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User)o;
        UserHistory userHistory = new UserHistory();
        //userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);  // INSERT
    }

}







