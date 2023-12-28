package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   // 스프링 context 를 로딩하여 테스트에 사용.
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){
        System.out.println("\n-- TEST#crud() ---------------------------------------------");

//        User user = new User();   // 그냥 Java 객체
//        userRepository.save(user);  // INSERT, 저장하기  (영속화!)
//        userRepository.findAll().forEach(System.out::println);  // SELECT, 전체 조회

        // 테스트에 사용할 변수들
        List<User> users = null;
        User user1 = null, user2 = null, user3 = null, user4 = null, user5 = null;
        List<Long> ids = null;

        // #002 finaAll()
//        users = userRepository.findAll();
//        users.forEach(System.out::println);

        // #003 findxx() + Sort.by() 사용
//        users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        users.forEach(System.out::println);

        // #004 findxxByxx(Iterable)
        // findAllById(Iterable<Long> ids)
//        ids = List.of(1L, 3L, 5L);
//        users = userRepository.findAllById(ids);
//        users.forEach(System.out::println);

        // #005 save(entity) 저장하기
//        user1 = new User("jack", "jack@redknight.com");
//        System.out.println("save() 전: " + user1);
//        userRepository.save(user1);  // 영속화된 객체
//        System.out.println("save() 후: " + user1);

        // #006 saveAll(Iterable<entity>)
//        user1 = new User("jack", "jack@redknight.com");
//        user2 = new User("steve", "steve@redknight.com");
//
//        users = List.of(user1, user2);
//        userRepository.saveAll(users);
//
//        userRepository.findAll().forEach(System.out::println);

        // TODO

        System.out.println("------------------------------------------------------------\n");
    }

} // end Test