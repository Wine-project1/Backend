package com.example.demo.dto.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookUpdateRequest {

    @NotBlank(message = "관리번호는 필수입니다.")
    private String manageNumber;

    @NotBlank(message = "도서명은 필수입니다.")
    private String title;
}
