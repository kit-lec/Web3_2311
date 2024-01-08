package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public QryCommentList list(Long id) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findByPost(id, Sort.by(Sort.Order.desc("id")));

        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");

        return list;
    }

    @Override
    public QryResult write(Long postId, Long userId, String content) {
        User user = userRepository.findById(userId).orElse(null);

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .post(postId)
                .build();

        commentRepository.save(comment);  // INSERT

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();

        return result;
    }

    @Override
    public QryResult delete(Long id) {

        Comment comment = commentRepository.findById(id).orElse(null);
        int count = 0;
        String status = "FAIL";

        if(comment != null){
            commentRepository.delete(comment);
            count = 1;
            status = "OK";
        }

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();

        return result;
    }
}










