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
@Entity
// ↑ 이 객체가 JPA 에서 관리하는 Entity 객체임을 알림.
@Table(name = "T_USER"   // db 테이블 명
        , indexes = {@Index(columnList = "name")}
        , uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "name"})}
)
@EntityListeners(value = MyEntityListener.class)
public class User implements Auditable{

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    private Long id;

    @NonNull
    private String name;
    @NonNull
    @Column(unique = true)
    private String email;

    //@Column(nullable = false)   // true 가 디폴트
    //@Column(updatable = false)
    private LocalDateTime createdAt;
    //@Column(insertable = false)
    private LocalDateTime updatedAt;

    // query method Empty 예제
//    @OneToMany(fetch = FetchType.EAGER)  // User:Address = 1:N
//    private List<Address> address;

//    @Transient  // jakarta.persistence : DB 에 반영안하는 필드
//    private String testData;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;


//    @PrePersist
//    public void prePersist() {
//        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>> preRemove");
//    }
//
//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>> postPersist");
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>> postUpdate");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>> postRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println(">>> postLoad");
//    }




}







