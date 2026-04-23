package com.example.demo.entity;

import com.example.demo.enums.RentalStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_loans")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @Column(name = "book_management_no")
    private String bookManagementNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus status;

    @Column(name = "loaned_at", nullable = false)
    private LocalDateTime loanedAt;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @Builder
    public Rental(User user, String bookTitle, String bookManagementNo) {
        this.user = user;
        this.bookTitle = bookTitle;
        this.bookManagementNo = bookManagementNo;
        this.status = RentalStatus.LOANED;
        this.loanedAt = LocalDateTime.now();
    }

    public void returnBook() {
        this.status = RentalStatus.RETURNED;
        this.returnedAt = LocalDateTime.now();
    }
}
