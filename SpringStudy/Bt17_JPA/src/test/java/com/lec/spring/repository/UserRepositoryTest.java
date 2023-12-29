package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest   // 스프링 context 를 로딩하여 테스트에 사용.
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 테스트에 사용할 변수들
    List<User> users;
    User user, user1, user2, user3, user4, user5;
    List<Long> ids;
    Optional<User> optUser;
    // @BeforeEach : 매 테스트 메소드가 실행되기 직전에 실행
    //  @BeforeEach 메소드의 매개변수에 TestInfo 객체를 지정하면
    // JUnit Jupiter 에선 '현재 실행할 test' 의 정보가 담긴 TestInfo 객체를 주입해준다
    @BeforeEach
    void beforeEach(TestInfo testInfo){
        System.out.println("-".repeat(40));

        users = null;
        user = user1 = user2 = user3 = user4 = user5 = null;
        ids = null;
        optUser = null;

        String displayName = testInfo.getDisplayName();
        System.out.println("[ " + displayName + " ] 호출");
    }

    // @AfterEach : 매 테스트 메소드가 종료된 직후에 실행
    @AfterEach
    void afterEach(){
        System.out.println("*".repeat(40) + "\n");
    }

    @Test
    void crud(){
        System.out.println("\n-- TEST#crud() ---------------------------------------------");

//        User user = new User();   // 그냥 Java 객체
//        userRepository.save(user);  // INSERT, 저장하기  (영속화!)
//        userRepository.findAll().forEach(System.out::println);  // SELECT, 전체 조회



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


    // deleteInBatch(Iterable<E>)
    // 위의 delete 와는 달리 하나의 DELETE 쿼리 실행
    @Test
    void test016(){
        
        userRepository.deleteInBatch(userRepository.findAllById(List.of(1L, 3L)));

        userRepository.findAll().forEach(System.out::println);
    }

    // #017 deleteAllInBatch()
    // 모든 entity 삭제
    @Test
    void test017(){
        userRepository.deleteAllInBatch();
        System.out.println(userRepository.count());   // 0
    }

    @Test
    void test018(){
        //TODO
    }

    
    // QueryByExample (QBE) 사용
    @Test
    void test019(){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")   // name 컬럼은 매칭하지 않겠다.
                .withMatcher("email", endsWith())  // email 컬럼의 뒷 부분 매칭으로 검색
                ;

        //Example.of(probe, ExampleMatcher)  <-- 여기서 probe 란 실제 Entity 는 아니란 말입니다.  match 를 위해서 쓰인 객체
        Example<User> example = Example.of(new User("ma", "knight.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void test020(){
        // email 필드에 "blue" 문자열이 있는 것들 SELECT 하기
        user1 = new User();
        user1.setEmail("blue");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("email", contains())
                ;
        Example<User> example = Example.of(user1, matcher);


        userRepository.findAll(example).forEach(System.out::println);
    }

    // UPDATE 동작
    // save() 가 INSERT 뿐 아니라 UPDATE 를 생성하기도 한다

    @Test
    void test021(){
        user1 = new User("david", "david@redknight.com");  // Java 개체 생성
        System.out.println("save() 전: " + user1);
        userRepository.save(user1); // INSERT  (Id 값이 null 일때)
        System.out.println("save() 후: " + user1);
        // 지금부터 user1 은 '영속화된 객체' 이다
        userRepository.findAll().forEach(System.out::println);

        user1.setEmail("DAV@mail.com");   // 영속화된 객체에 변경사항 발생!
        userRepository.save(user1);   // UPDATE 발생! (Id 값이 있을때)
                                        // SELECT 수행후에 UPDATE 수행
        userRepository.findAll().forEach(System.out::println);
    }


    /***********************************************************************
     * Query Method
     */

    @Test
    void qryMethod001() {
        //TODO
    }

    // 쿼리 메소드의 naming
    //  https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html
    //     find…By, read…By, get…By, query…By, search…By, stream…By
    @Test
    void qryMethod002() {
        System.out.println("findByEmail : " + userRepository);
        System.out.println("getByEmail : " + userRepository);
        System.out.println("readByEmail : " + userRepository);
        System.out.println("queryByEmail : " + userRepository);
        System.out.println("searchByEmail : " + userRepository);
        System.out.println("streamByEmail : " + userRepository);
        System.out.println("findUserByEmail : " + userRepository);
    }

    @Test
    void qryMethod003() {
        System.out.println("findSomethingByEmail : " + userRepository);

        System.out.println("findByByName : " + userRepository);
    }

    @Test
    void qryMethod005() {
        System.out.println("findTop1ByName : " + userRepository);
        System.out.println("findTop2ByName : " + userRepository);
        System.out.println("findFirst1ByName : " + userRepository);
        System.out.println("findFirst2ByName : " + userRepository);
    }

    @Test
    void qryMethod006() {
        System.out.println("findLast1ByName : " + userRepository);
    }

    @Test
    void qryMethod007() {
        System.out.println("findByEmailAndName : " + userRepository);
        System.out.println("findByEmailAndName : " + userRepository);
        System.out.println("findByEmailOrName : " + userRepository);
    }

    // After, Before
    // After, Before 는 주로 '시간'에 대해서 쓰이는 조건절에 쓰인다.  (가독성 측면)
    // 그러나, 꼭 '시간'만을 위해서 쓰이지는 않는다 .   '숫자', '문자열' 등에서도 쓰일수 있다.
    @Test
    void qryMethod008() {
        System.out.println("findByCreatedAtAfter : " + userRepository);  // 어제 이후의 createdAt 값
        System.out.println("findByIdAfter : " + userRepository);
        System.out.println("findByNameBefore : " + userRepository);
    }

    // GreaterThan, GreaterThanEqual, LessThan, LessThanEqual
    @Test
    void qryMethod009() {
        System.out.println("findByCreatedAtGreaterThan : " + userRepository);
        System.out.println("findByNameGreaterThanEqual : " + userRepository);
    }

    // Between
    @Test
    void qryMethod010() {
        System.out.println("findByCreatedAtBetween : " + userRepository);
        System.out.println("findByIdBetween : " + userRepository);
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository);
    }

    // Empty 와 Null
    //   - IsEmpty, Empty
    //   - IsNotEmpty, NotEmpty,
    //   - NotNull, IsNotNull
    //   - Null, IsNull
    @Test
    void qryMethod011() {
        System.out.println("findByIdIsNotNull : " + userRepository);

//      System.out.println("findByAddressIsNotEmpty : " + userRepository);
    }

    // In, NotIn
    @Test
    void qryMethod012() {
        System.out.println("findByNameIn : " + userRepository);
    }

    // StartingWith, EndingWith, Contains
    // 문자열에 대한 검색쿼리, LIKE 사용
    @Test
    void qryMethod013() {
        System.out.println("findByNameStartingWith : " + userRepository);
        System.out.println("findByNameEndingWith : " + userRepository);
        System.out.println("findByEmailContains : " + userRepository);
    }

    // Like
    // 사실 위의 키워드는 Like 를 한번 더 wrapping 한거다.
    @Test
    void qryMethod014() {
        System.out.println("findByEmailLike : " + userRepository);
    }

    // Is, Equals
    // 특별한 역할은 하지 않는다. 생략가능. 가독성 차원에서 남겨진 키워드입니다.
    @Test
    void qryMethod015() {
        System.out.println(userRepository);
    }

    /***********************************************************************
     * Query Method - Sorting & Paging
     */

    // Naming 기반 정렬
    // Query method 에 OrderBy 를 붙임
    @Test
    void qryMethod016() {
        System.out.println("findTop1ByName : " + userRepository);
        System.out.println("findLast1ByName : " + userRepository);

        System.out.println("findTopByNameOrderByIdDesc : " + userRepository);
    }

    // 정렬기준 추가
    @Test
    void qryMethod017() {
        System.out.println("findFirstByNameOrderByIdDesc : " + userRepository);
        System.out.println("findFirstByNameOrderByIdDescEmailDesc : " + userRepository);
    }

    // 매개변수(Sort) 기반 정렬
    // Query method 에 Sort 매개변수로 정렬옵션 제공
    @Test
    void qryMethod018() {
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
    }

    @Test
    void qryMethod018_2() {
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
        System.out.println("findFirstByName + Sort : "
                + userRepository);
    }

    @Test
    void qryMethod018_3() {
        System.out.println("findFirstByName + Sort : "
                + userRepository);
    }

    // TODO

    // 19 Paging + Sort
    // PageRequest.of(page, size, Sort) page는 0-base
    // PageRequest 는 Pageable의 구현체
    @Test
    void qryMethod019() {
        // TODO
    }



} // end Test