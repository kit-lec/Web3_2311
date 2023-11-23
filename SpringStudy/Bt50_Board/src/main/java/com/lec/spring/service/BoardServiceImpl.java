package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

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

    // 페이징 목록
    @Override
    public List<Post> list(Integer page, Model model) {
        // 현재 페이지 parameter
        if(page == null) page = 1;   // 디폴트 1 page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS; // 만약 session 에 없으면 기본값으로 동작
        session.setAttribute("page", page);  // 현재 페이지 번호 -> session 에 저장

        long cnt = postRepository.countAll();   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);  // 총 몇 '페이지' 분량인가?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막 페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Post> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            // page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            // 해당 페이지의 글 목록 읽어오기
            list = postRepository.selectFromRow(fromRow, pageRows);
            model.addAttribute("list", list);
        } else {
            page = 0;
        }

        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        // [페이징]
        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지

        return list;
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












