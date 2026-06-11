package com.example.demo.entity;

import com.example.demo.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_loans")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false, length = 255)
    private String bookTitle;

    @Column(length = 50)
    private String manageNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus status = RentalStatus.LOANED;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime loanedAt;

    private LocalDateTime returnedAt;

    @Builder
    public Rental(User user, Book book, String bookTitle, String manageNumber) {
        this.user = user;
        this.book = book;
        this.bookTitle = bookTitle;
        this.manageNumber = manageNumber;
        this.status = RentalStatus.LOANED;
    }

    public void markReturned() {
        this.status = RentalStatus.RETURNED;
        this.returnedAt = LocalDateTime.now();
    }
}
