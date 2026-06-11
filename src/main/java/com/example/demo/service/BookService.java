package com.example.demo.service;

import com.example.demo.dto.book.BookCreateRequest;
import com.example.demo.dto.book.BookResponse;
import com.example.demo.dto.book.BookUpdateRequest;
import com.example.demo.entity.Book;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        if (bookRepository.existsByManageNumber(request.getManageNumber())) {
            throw new CustomException(ErrorCode.DUPLICATE_MANAGE_NUMBER);
        }

        Book book = request.toEntity();
        bookRepository.save(book);
        return BookResponse.from(book);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        // 관리번호 변경 시 중복 체크
        if (!book.getManageNumber().equals(request.getManageNumber())
                && bookRepository.existsByManageNumber(request.getManageNumber())) {
            throw new CustomException(ErrorCode.DUPLICATE_MANAGE_NUMBER);
        }

        book.update(request.getManageNumber(), request.getTitle());
        return BookResponse.from(book);
    }
}
