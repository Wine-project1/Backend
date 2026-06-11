package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),

    // 사용자
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    // 도서
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "도서를 찾을 수 없습니다."),
    BOOK_NOT_AVAILABLE(HttpStatus.CONFLICT, "현재 대출 중인 도서입니다."),
    DUPLICATE_MANAGE_NUMBER(HttpStatus.CONFLICT, "이미 존재하는 관리번호입니다."),

    // 대출
    RENTAL_NOT_FOUND(HttpStatus.NOT_FOUND, "대출 내역을 찾을 수 없습니다."),
    RENTAL_ALREADY_RETURNED(HttpStatus.CONFLICT, "이미 반납된 도서입니다.");

    private final HttpStatus status;
    private final String message;
}
