package com.lec.spring;

import com.lec.spring.domain.Authority;
import com.lec.spring.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaBoardTest {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void init(){
        System.out.println("[init]");

        // Authority 생성
        Authority auth_member = Authority.builder().name("ROLE_MEMBER").build();
        Authority auth_admin = Authority.builder().name("ROLE_ADMIN").build();

        authorityRepository.saveAndFlush(auth_member);  // INSERT
        authorityRepository.saveAndFlush(auth_admin);  // INSERT

        authorityRepository.findAll().forEach(System.out::println);


        System.out.println("─".repeat(45) + "\n");
    }

}
