package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

// Repository 생성
// JpaRepository<Entity타입, Id타입> 상속 ← 바로 이게 Spring Data JPA 가 지원해주는 영역!
//   상속받은 것만으로도 많은 JPA 메소드를 편리하게 사용 가능하게 된다.
//   상속받은 것만으로도 이미 Spring Context 에 생성된다.
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByName(String name);   // 복수개를 SELECT 하게 되는 경우 에러 발생
    //List<User> findByName(String name);
//    Optional<User> findByName(String name);
    Set<User> findByName(String name);

    // 2.
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);   // 복수개 리턴하는 경우 findUsersByEmail <- 이렇게 작명하는게 좋다.

    // 3. find아무말By...
    User findSomethingByEmail(String email);

    // 4. 잘못된 네이밍.  스프링 가동 자체가 안된다.
//    User findByByName(String name);   // <- 'ByName' 이라는 property 를 찾게 된다.

    // 5. First, Top
    List<User> findFirst1ByName(String name);
    List<User> findFirst2ByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findTop2ByName(String name);

    // 6. Last
    // Last 는 없는 키워드.  없는 키워드는 무시된다.
    List<User> findLast1ByName(String name);   // 걍 findByName() 과 동일한 동작

    // 7. And, Or
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    // 8. After, Before
    List<User> findByCreatedAtAfter(LocalDateTime dateTime);
    List<User> findByIdAfter(Long id);
    List<User> findByNameBefore(String name);

    //9. GreaterThan, GreaterThanEqual, LessThan, LessThanEqual
    List<User> findByCreatedAtGreaterThan(LocalDateTime datetime);
    List<User> findByNameGreaterThanEqual(String name);

    // 10. Between
    List<User> findByCreatedAtBetween(LocalDateTime datetime1, LocalDateTime datetime2);
    List<User> findByIdBetween(Long id1, Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    // 11. Null, Empty
    List<User> findByIdIsNotNull();
    
    // 스프링 부트 가동시 에러
//    List<User> findByIdIsNotEmpty();
//    List<User> findByAddressIsNotEmpty();

    // 12. In, NotIn
    List<User> findByNameIn(List<String> names);

    // 13. StartingWith, EndingWith, Contains
    // 문자열에 대한 검색쿼리, LIKE 사용
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByEmailContains(String email);

    // 14. Like
    List<User> findByEmailLike(String email);

    // 16. OrderBy
    List<User> findTopByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDesc(String name);

    // 17. 정렬기준 추가
    List<User> findFirstByNameOrderByIdDescEmailDesc(String name);

    // 18. 매개변수 (Sort) 기반 정렬
    List<User> findFirstByName(String name, Sort sort);

    // 19. Paging
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "SELECT * FROM t_user LIMIT 1", nativeQuery = true)
    Map<String, Object> findRowRecord();

}











