package com.example.demo.dto.book;

import com.example.demo.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookResponse {

    private Long id;
    private String manageNumber;
    private String title;
    private Boolean isAvailable;
    private LocalDateTime createdAt;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .manageNumber(book.getManageNumber())
                .title(book.getTitle())
                .isAvailable(book.getIsAvailable())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
