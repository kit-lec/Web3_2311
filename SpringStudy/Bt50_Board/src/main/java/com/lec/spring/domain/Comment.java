package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;  // PK

    @ToString.Exclude
    private User user;   // 댓글 작성자 (FK)
    private Long post_id;   // 어느글의 댓글 (FK)

    private String content;  // 댓글 내용
    private LocalDateTime regDate;  // 댓글 작성일시
}
