package com.example.demo.dto.rental;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentalCreateRequest {

    @NotBlank(message = "도서명은 필수입니다.")
    private String bookTitle;

    private String manageNumber;
}
