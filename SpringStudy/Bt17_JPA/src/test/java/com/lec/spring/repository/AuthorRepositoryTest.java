package com.lec.spring.repository;

import com.lec.spring.domain.Author;
import com.lec.spring.domain.Book;
import com.lec.spring.domain.Writing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WritingRepository writingRepository;

    @Test
    @Transactional
    void manyToManyTest(){
        System.out.println("-".repeat(35));

        Book book1 = givenBook("요리책1");
        Book book2 = givenBook("요리책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steve");

//        book1.addAuthor(author1);  // martin 저
//        book2.addAuthor(author2); // steve 저
//        book3.addAuthor(author1, author2);  // martin, steve
//        book4.addAuthor(author1, author2);  // martin, steve
//
//        // martin 이 작성한 책들
//        author1.addBook(book1, book3, book4);
//        // steve 가 작성한 책들
//        author2.addBook(book2, book3, book4);

        Writing writing1 = givenWriting(book1, author1);
        Writing writing2 = givenWriting(book2, author2);
        Writing writing3 = givenWriting(book3, author1);
        Writing writing4 = givenWriting(book3, author2);
        Writing writing5 = givenWriting(book4, author1);
        Writing writing6 = givenWriting(book4, author2);

        // Book 에 Writing 을 매핑
        book1.addWriting(writing1);
        book2.addWriting(writing2);
        book3.addWriting(writing3, writing4);
        book4.addWriting(writing5, writing6);

        // Author  에 Writing 을 매핑
        author1.addWritings(writing1, writing3, writing5);
        author2.addWritings(writing2, writing4, writing6);


        // book, author 저장  (UPDATE)
        bookRepository.saveAll(List.of(book1, book2, book3, book4));
        authorRepository.saveAll(List.of(author1, author2));

//        // 세번째 책의 저자들  Book -> Author
//        System.out.println("=".repeat(35));
//        System.out.println(bookRepository.findAll().get(2).getAuthors());
//
//        // 첫번째 저자의 책들  Author -> Book
//        System.out.println("=".repeat(35));
//        System.out.println(authorRepository.findAll().get(0).getBooks());

        // 세번째 책의 저자들  Book -> Author
        System.out.println("=".repeat(35));
        bookRepository.findAll().get(2).getWritings()
                .forEach(writing -> System.out.println(writing.getAuthor()));

        // 첫번째 저자의 책들  Author -> Book
        System.out.println("=".repeat(35));
        authorRepository.findAll().get(0).getWritings()
                .forEach(writing -> System.out.println(writing.getBook()));

        System.out.println("-".repeat(35));
    }

    // 테스트용 데이터 입력 메소드
    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book); // INSERT
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author); // INSERT
    }

    private Writing givenWriting(Book book, Author author) {
        Writing writing = new Writing();
        writing.setBook(book);
        writing.setAuthor(author);

        return writingRepository.save(writing);
    }


}