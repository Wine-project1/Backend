package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String manageNumber;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Book(String manageNumber, String title) {
        this.manageNumber = manageNumber;
        this.title = title;
        this.isAvailable = true;
    }

    public void update(String manageNumber, String title) {
        this.manageNumber = manageNumber;
        this.title = title;
    }

    public void markUnavailable() {
        this.isAvailable = false;
    }

    public void markAvailable() {
        this.isAvailable = true;
    }
}
