package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Model 객체 (domain)

/**
 * DTO 객체
 *  : Data Transfer Object 라고도 함.
 *
 *  객체 -> DB
 *  DB -> 객체
 *  request -> 객체
 *  객체 -> response
 *  ..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String subject;
    private String content;
    private LocalDateTime regDate;
    private Long viewCnt;

    private User user;   // 글 작성자 (FK)
}
