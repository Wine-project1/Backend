package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Book {
//    컬럼명	타입	제약	설명
//    id	BIGINT	PK, AUTO_INCREMENT	고유 식별자
//    manage_number	VARCHAR(50)	UNIQUE, NOT NULL	관리번호
//    title	VARCHAR(255)	NOT NULL	도서명
//    is_available	BOOLEAN	DEFAULT TRUE	대출 가능 여부
//    created_at	DATETIME	NOT NULL	등록일시


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
