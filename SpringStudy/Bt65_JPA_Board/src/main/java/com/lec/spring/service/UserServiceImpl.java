package com.lec.spring.service;

import com.lec.spring.domain.Authority;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AuthorityRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username.toUpperCase());
    }

    @Override
    public boolean isExist(String username) {
        User user = findByUsername(username.toUpperCase());
        return (user != null);
    }

    @Override
    public int register(User user) {
        user.setUsername(user.getUsername().toUpperCase());   // DB 에는 회원아이디(username) 을 대문자로 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // password 는 PasswordEncoder 로 암호화 하여 저장해야 한다!
        userRepository.save(user);   // 새로이 회원 저장 (INSERT),  id 값 받아온다.

        // 신규회원은 ROLE_MEMBER 권한 기본적으로 부여하기
        Authority auth = authorityRepository.findByName("ROLE_MEMBER");
        user.addAuthority(auth);
        userRepository.save(user);

        return 1;
    }

    @Override
    public List<Authority> selectAuthoritiesById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null)
            return user.getAuthorities();

        return new ArrayList<>();
    }
}
