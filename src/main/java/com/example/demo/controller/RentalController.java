package com.example.demo.controller;

import com.example.demo.dto.rental.RentalCreateRequest;
import com.example.demo.dto.rental.RentalResponse;
import com.example.demo.enums.RentalStatus;
import com.example.demo.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    // 대출 목록 조회 - 학생(본인) / 관리자(전체)
    @GetMapping
    public ResponseEntity<List<RentalResponse>> getRentals(
            Authentication authentication,
            @RequestParam(required = false) RentalStatus status) {

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            if (status != null) {
                return ResponseEntity.ok(rentalService.getRentalsByStatus(status));
            }
            return ResponseEntity.ok(rentalService.getAllRentals());
        }

        return ResponseEntity.ok(rentalService.getMyRentals(authentication.getName()));
    }

    // 도서 대출 신청 (학생)
    @PostMapping
    public ResponseEntity<RentalResponse> createRental(
            Authentication authentication,
            @Valid @RequestBody RentalCreateRequest request) {
        return ResponseEntity.ok(rentalService.createRental(authentication.getName(), request));
    }

    // 반납 처리 (관리자)
    @PatchMapping("/{id}/return")
    public ResponseEntity<RentalResponse> returnRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.returnRental(id));
    }
}
