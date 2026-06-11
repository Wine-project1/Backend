package com.example.demo.controller;

import com.example.demo.dto.book.BookCreateRequest;
import com.example.demo.dto.book.BookResponse;
import com.example.demo.dto.book.BookUpdateRequest;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 도서 목록 조회 (학생/관리자)
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // 도서 등록 (관리자)
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // 도서 수정 (관리자)
    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,
                                                   @Valid @RequestBody BookUpdateRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
}
