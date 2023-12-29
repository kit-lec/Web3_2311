package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity(name = "T_USER")  // 물리적으로 생성되는 테이블 이름
public class User {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // query method Empty 예제
//    @OneToMany(fetch = FetchType.EAGER)  // User:Address = 1:N
//    private List<Address> address;

}







