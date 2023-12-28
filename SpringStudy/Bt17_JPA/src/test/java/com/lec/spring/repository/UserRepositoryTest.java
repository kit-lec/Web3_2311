package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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

        // #008 findById(id)
        // 리턴타입은 java.util.Optional<entity>
//        Optional<User> user;
//        user = userRepository.findById(1L);
//        System.out.println(user);  // Optional<>
//        System.out.println(user.get());  // User
//        System.out.println();
//
//        user = userRepository.findById(10L);
//        System.out.println(user);  // Optional.empty
//        System.out.println();
//
//        // Optional#orElse() : 찾으면 entity 리턴, 없는 경우 null 리턴하도록 처리.
//        user1 = userRepository.findById(10L).orElse(null);
//        System.out.println(user1);  // null
//        System.out.println();

        // #009 flush()
        // flush() 는 SQL쿼리의 변화를 주는게 아니라 DB 반영 시점을 조정한다. 로그 출력으로는 변화를 확인하기 힘들다
//        userRepository.save(new User("new martin", "newmartin@redknight.com"));
//        userRepository.flush();
//        userRepository.findAll().forEach(System.out::println);
//
//        // saveAndFlush() = save() + flush()
//        userRepository.saveAndFlush(new User("joseph", "joseph@bluedragin.com"));
//        userRepository.findAll().forEach(System.out::println);

        // #010 count()
//        Long count = userRepository.count();
//        System.out.println(count);

        // #011 existsById()
//        Boolean exists = userRepository.existsById(1L);
//        System.out.println(exists);

        // #011 delete(entity)  -> 리턴값이 없다 void
        // 삭제하기
//        user1 = userRepository.findById(1L).orElse(null);  // SELECT
//        userRepository.delete(user1);  // SELECT + DELETE
//        userRepository.findAll().forEach(System.out::println);
//
//        userRepository.delete(user1);  // SELECT 만 수행함. 수행결과 데이터가 없으므로 DELETE 수행안함

        // delete(entity)  에서 null 을 허용하지 않기 때문에.  차라리 예외를 발생시켜 처리할수도 있다.
//        userRepository.delete(null); // InvalidDataAccessApiUsageException: Entity must not be null

//        try{
//            userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        } catch (RuntimeException e){
//            System.out.println("delete() 결과 삭제된 데이터가 없습니다.");
//        }

        // #013 deleteById(id)  -> 리턴값 없다 void
//        userRepository.deleteById(1L);
//        userRepository.findAll().forEach(System.out::println);
//
//        userRepository.deleteById(10L);  // 없는 데이터.  SELECT 만 하고 끝남

        // #014 deleteAll()
//        userRepository.deleteAll();  // -> delete 쿼리를 매번 수행한다!!  헉!?
//        System.out.println(userRepository.count());  // 0

        // #015 deleteAll(iterable)
        userRepository.deleteAll(userRepository.findAllById(List.of(1L, 3L)));
        userRepository.findAll().forEach(System.out::println);

        // TODO

        System.out.println("------------------------------------------------------------\n");
    }

} // end Test