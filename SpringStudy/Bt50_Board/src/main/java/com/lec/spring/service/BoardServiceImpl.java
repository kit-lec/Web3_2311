package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println("BoardService() 생성");
    }

    @Override
    public int write(Post post) {
        // 현재 로그인한 작성자 정보
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다.
        user = userRepository.findById(user.getId());
        post.setUser(user);    // 글 작성자 세팅

        int cnt = postRepository.save(post);

        return cnt;
    }

    
    @Override
    @Transactional   // 이 메소드를 트랜잭션으로 처리
    public Post detail(Long id) {
        postRepository.incViewCnt(id);
        Post post = postRepository.findById(id);
        return post;
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }

    @Override
    public Post selectById(Long id) {
        Post post = postRepository.findById(id);
        return post;
    }

    @Override
    public int update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public int deleteById(Long id) {
        int result = 0;

        Post post = postRepository.findById(id);  // 존재하는 데이터인지 읽어와보기
        if(post != null){
            result = postRepository.delete(post);
        }

        return result;
    }
}












