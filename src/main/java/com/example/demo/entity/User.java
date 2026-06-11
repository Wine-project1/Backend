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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
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
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
