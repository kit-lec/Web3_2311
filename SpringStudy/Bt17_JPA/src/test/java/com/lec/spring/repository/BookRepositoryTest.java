package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import com.lec.spring.domain.Publisher;
import com.lec.spring.domain.Review;
import com.lec.spring.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void beforeEach(TestInfo testInfo){
        System.out.println("-".repeat(40));
        String displayName = testInfo.getDisplayName();
        System.out.println("[ " + displayName + " ] 호출");
    }

    // @AfterEach : 매 테스트 메소드가 종료된 직후에 실행
    @AfterEach
    void afterEach(){
        System.out.println("*".repeat(40) + "\n");
    }


    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("JPA 스터디");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);
        bookRepository.save(book);   // INSERT

        System.out.println(bookRepository.findAll());
    }

    //----------------------------------------------------
    // 테스트용 데이터
    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("K-출판사");

        return publisherRepository.save(publisher);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 완전정복");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User givenUser() {
        // 1번 User값 리턴
        return userRepository.findByEmail("martin@redknight.com");
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이었어요");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    // 테스트용 데이터 빌드
    private void givenBookAndReview() {
        // 리뷰 저장
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    @Test
    @Transactional // 지금은 OneToMany 에서 발생하는 LazyInitializationException 을 막기위해 사용
    void bookRelationTest(){
        givenBookAndReview();

        // 특정 User
        User user = userRepository.findByEmail("martin@redknight.com");

        // 특정 User 간 남긴 Review 들 가져오기
        // getter 호출하면 join 사용하여 User의 Review 을을 읽어온다.
        System.out.println("Review: " + user.getReviews());

        // 특정 User 가 남긴 Review 중 첫번째 Review 의 Book 정보
        System.out.println("Review: " + user.getReviews().get(0).getBook());

        // 특정 User 가 남긴 Review 중 첫번째 Review 의 Book 의 Publisher 정보
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());



    }



}












