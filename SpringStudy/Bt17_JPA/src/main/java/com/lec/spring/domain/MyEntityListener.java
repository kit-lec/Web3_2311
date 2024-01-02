package com.lec.spring.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

// Entity 가 아닌객체임에도
// @PrePersist, @PreUpdate 지정가능
public class MyEntityListener {

    @PrePersist
    public void prePersist(Object o){
        System.out.println(">> MyEntityListener#prePersist()");
        if(o instanceof Auditable){
            ((Auditable)o).setCreatedAt(LocalDateTime.now());
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        System.out.println(">> MyEntityListener#preUpdate()");
        if(o instanceof Auditable){
//            ((Auditable)o).setCreatedAt(LocalDateTime.now());
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }



}













