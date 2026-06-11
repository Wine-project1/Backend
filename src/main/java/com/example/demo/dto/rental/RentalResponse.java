package com.example.demo.dto.rental;

import com.example.demo.entity.Rental;
import com.example.demo.enums.RentalStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RentalResponse {

    private Long id;
    private String userId;
    private String userName;
    private Integer grade;
    private Integer classNum;
    private String bookTitle;
    private String manageNumber;
    private RentalStatus status;
    private LocalDateTime loanedAt;
    private LocalDateTime returnedAt;

    public static RentalResponse from(Rental rental) {
        return RentalResponse.builder()
                .id(rental.getId())
                .userId(rental.getUser().getUserId())
                .userName(rental.getUser().getName())
                .grade(rental.getUser().getGrade())
                .classNum(rental.getUser().getClassNum())
                .bookTitle(rental.getBookTitle())
                .manageNumber(rental.getManageNumber())
                .status(rental.getStatus())
                .loanedAt(rental.getLoanedAt())
                .returnedAt(rental.getReturnedAt())
                .build();
    }
}
