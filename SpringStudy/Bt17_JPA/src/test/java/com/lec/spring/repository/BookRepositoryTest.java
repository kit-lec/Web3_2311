package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

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


    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("JPA 스터디");
        book.setAuthor("제임스");
        bookRepository.save(book);   // INSERT

        System.out.println(bookRepository.findAll());
    }

}












