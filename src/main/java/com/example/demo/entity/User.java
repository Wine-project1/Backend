package com.example.demo.entity;

import com.example.demo.dto.auth.UserSaveRequestDto;
import com.example.demo.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
//    users (계정)
//    컬럼명	타입	제약	설명
//    id	BIGINT	PK, AUTO_INCREMENT	고유 식별자 o
//    student_id	VARCHAR(20)	UNIQUE, NOT NULL	학번
//    name	VARCHAR(50)	NOT NULL	이름
//    phone	VARCHAR(20)	UNIQUE, NOT NULL	전화번호
//    password	VARCHAR(255)	NOT NULL	BCrypt 해시

//    role	ENUM	NOT NULL	STUDENT / ADMIN
//    grade	TINYINT		학년
//    class_num	TINYINT		반
//    number	TINYINT		번호
//    is_active	BOOLEAN	DEFAULT TRUE	계정 활성 여부
//    created_at	DATETIME	NOT NULL	생성일시

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false, unique = true, length = 20)
    private String userId;   //stu250307

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 20,unique = true)
    private String phone;

    @Column(nullable = false)
    private String password = "1234";

    @Transient
    private boolean passwordEncoded;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.STUDENT;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "class_num", nullable = false)
    private Integer classNum;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;




}
