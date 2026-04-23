package com.example.demo.dto.auth;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    private String userId;
    @NonNull
    private String password;
}
