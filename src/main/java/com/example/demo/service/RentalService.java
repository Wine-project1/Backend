package com.example.demo.service;

import com.example.demo.dto.rental.RentalCreateRequest;
import com.example.demo.dto.rental.RentalResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.enums.RentalStatus;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // 학생: 본인 대출 목록 조회
    public List<RentalResponse> getMyRentals(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return rentalRepository.findByUserIdOrderByLoanedAtDesc(user.getId()).stream()
                .map(RentalResponse::from)
                .collect(Collectors.toList());
    }

    // 관리자: 전체 대출 목록 조회
    public List<RentalResponse> getAllRentals() {
        return rentalRepository.findAllByOrderByLoanedAtDesc().stream()
                .map(RentalResponse::from)
                .collect(Collectors.toList());
    }

    // 관리자: 상태별 대출 목록 필터링
    public List<RentalResponse> getRentalsByStatus(RentalStatus status) {
        return rentalRepository.findByStatusOrderByLoanedAtDesc(status).stream()
                .map(RentalResponse::from)
                .collect(Collectors.toList());
    }

    // 학생: 도서 대출 신청
    @Transactional
    public RentalResponse createRental(String userId, RentalCreateRequest request) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Book book = null;
        if (request.getManageNumber() != null && !request.getManageNumber().isBlank()) {
            book = bookRepository.findByManageNumber(request.getManageNumber())
                    .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

            if (!book.getIsAvailable()) {
                throw new CustomException(ErrorCode.BOOK_NOT_AVAILABLE);
            }

            book.markUnavailable();
        }

        Rental rental = Rental.builder()
                .user(user)
                .book(book)
                .bookTitle(request.getBookTitle())
                .manageNumber(request.getManageNumber())
                .build();

        rentalRepository.save(rental);
        return RentalResponse.from(rental);
    }

    // 관리자: 반납 처리
    @Transactional
    public RentalResponse returnRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new CustomException(ErrorCode.RENTAL_NOT_FOUND));

        if (rental.getStatus() == RentalStatus.RETURNED) {
            throw new CustomException(ErrorCode.RENTAL_ALREADY_RETURNED);
        }

        rental.markReturned();

        if (rental.getBook() != null) {
            rental.getBook().markAvailable();
        }

        return RentalResponse.from(rental);
    }
}
