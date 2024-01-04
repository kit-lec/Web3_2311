package com.lec.spring.domain;

import com.lec.spring.listener.Auditable;
import com.lec.spring.listener.UserEntityListener;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString(callSuper = true)  // 부모까지 toString 출력
@EqualsAndHashCode(callSuper = true)
@Entity
// ↑ 이 객체가 JPA 에서 관리하는 Entity 객체임을 알림.
@Table(name = "T_USER"   // db 테이블 명
        , indexes = {@Index(columnList = "name")}
        , uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "name"})}
)
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    private Long id;

    @NonNull
    private String name;
    @NonNull
    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    // User:UserHistory = 1:N
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)   // Entity 가 어떤 컬럼으로 join 하게 될지 지정해준다.
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();  // NPE 방지

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


    //@Column(nullable = false)   // true 가 디폴트
    //@Column(updatable = false)
//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    // query method Empty 예제
//    @OneToMany(fetch = FetchType.EAGER)  // User:Address = 1:N
//    private List<Address> address;

//    @Transient  // jakarta.persistence : DB 에 반영안하는 필드
//    private String testData;




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







