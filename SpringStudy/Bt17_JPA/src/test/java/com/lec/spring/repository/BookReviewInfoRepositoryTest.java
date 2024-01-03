package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import com.lec.spring.domain.BookReviewInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

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

//    @Test
//    void crudTest1(){
//        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
//        bookReviewInfo.setAverageReviewScore(4.5f);
//        bookReviewInfo.setReviewCnt(2);
//
//        bookReviewInfoRepository.save(bookReviewInfo);  // INSERT
//
//        bookReviewInfoRepository.findAll().forEach(System.out::println);
//    }
//
//    @Test
//    void crudTest2(){
//        // ① 새로운 Book 생성
//        Book book = new Book();
//        book.setName("JPA 완전정복");
//        book.setAuthorId(1L);
//        book.setPublisherId(1L);
//
//        bookRepository.save(book); // Book 에 INSERT, 과연 id 값은?
//        Long newBookId = book.getId();
//        System.out.println(">>> New Book Id : " + newBookId);
//
//        // 2. 새로운 BookReviewInfo 생성
//        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(newBookId);   // ★Book 에 연결★
//        bookReviewInfo.setAverageReviewScore(4.5f);
//        bookReviewInfo.setReviewCnt(2);
//
//        bookReviewInfoRepository.save(bookReviewInfo);   // INSERT
//        Long newBookReviewId = bookReviewInfo.getId();
//        System.out.println(">>> New BookReview Id : " + newBookReviewId);
//
//        bookReviewInfoRepository.findAll().forEach(System.out::println);
//
//        // 자식 -> 부모 참조
//        Long parentBookId = bookReviewInfoRepository.findById(newBookReviewId)
//                                .orElseThrow(RuntimeException::new).getBookId();
//
//        Book result = bookRepository.findById(parentBookId).orElseThrow(RuntimeException::new);
//        System.out.println(">>> " + result);
//    }


    private Book givenBook(){
        Book book = new Book();
        book.setName("JPA 완전정복");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        // save() 의 리턴값은 영속화된 Entity 다.
        return bookRepository.save(book); // Book 에 INSERT
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());  // ★setBook (영속화된 Entity)★
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCnt(2);

        bookReviewInfoRepository.save(bookReviewInfo);  // INSERT

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }


    @Test
    void crudTest3(){
        givenBookReviewInfo();

        // BookReviewInfo 에서 Book 정보 가져오기
        // 자식 -> 부모 정보 조회하기
        var result = bookReviewInfoRepository
                .findById(1L).orElseThrow(RuntimeException::new)  // BookReviewInfo(id=1L)
                //.getBook()     // 1:1 연결된(참조하는) 부모 Book Entity 를 가져온다.
                ;

        System.out.println(">>> " + result);

        // Book 에서 BookReviewInfo 정보 조회
        // 부모 -> 자식
        var result2 = bookRepository.findById(1L).orElseThrow(RuntimeException::new)
                //.getBookReviewInfo()
                ;

        System.out.println(">>> " + result2);
    }

}










