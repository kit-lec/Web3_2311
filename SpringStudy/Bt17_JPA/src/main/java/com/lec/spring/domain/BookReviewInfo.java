package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1:1 연결하기
    // BookReviewInfo : Book
    // private Long bookId;

    @OneToOne  // Book 과 1:1 매핑
    private Book book;  // 부모 Entity 를 직접 참조한다.
                        // 기본적으로 Entity 에선 직접적으로 다른 Entity 를 직접 참조 못한다
                        // @OneToOne 과 같은 relation 어노테이션을 지정해주어야 한다.
                        // 이후 Entity 에서 해당 참조 객체에 대해 getter, settter 호출 하면
                        // JPA 에선 Relation 을 '자동 처리' 해줍니다.

    // primitive 로 선언하면 NOT NULL
    private float averageReviewScore;
    private int reviewCnt;
}
