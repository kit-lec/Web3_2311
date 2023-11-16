package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // 스프링 context 를 로딩하여 테스트에 사용
class PostRepositoryTest {

    // MyBatis 가 생성한 구현체들이 담겨 있는 SqlSession 객체
    // 기본적으로 스프링에 빈 생성되어있어서 주입 받을수 있다
    @Autowired
    private SqlSession sqlSession;

    @Test
    void test1(){
        PostRepository postRepository = sqlSession.getMapper(PostRepository.class);

        System.out.println("[최초]");
        postRepository.findAll().forEach(e -> System.out.println(e));

        Post post = Post.builder()
                .user("양효준")
                .subject("자바자바")
                .content("스프링스프링")
                .build();

        System.out.println("[생성전] " + post);
        postRepository.save(post);  // DB 에서 생성된 id 값이 담겨 있다.
        System.out.println("[생성후] " + post);

        System.out.println("[신규 생성후]");
        postRepository.findAll().forEach(e -> System.out.println(e));

        Long id = post.getId();
        for(int i = 0; i < 5; i++){
            postRepository.incViewCnt(id);
        }
        post = postRepository.findById(id);
        System.out.println("[조회수 증가후] " + post);

        post.setContent("감기 조심하세요");
        post.setSubject("덜덜덜덜");
        postRepository.update(post);
        post = postRepository.findById(id);
        System.out.println("[수정후] " + post);

        postRepository.delete(post);
        System.out.println("[삭제후]");
        postRepository.findAll().forEach(e -> System.out.println(e));


        System.out.println("테스트 완료");
    }


}







