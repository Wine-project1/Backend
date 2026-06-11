package com.example.demo.controller;


import com.example.demo.dto.auth.AuthResponse;
import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.entity.Reservation;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    //refresh 토큰 설정은 나중에

    //로그인 임시
    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@Valid @RequestBody LoginRequest request) {
        authService.doLogin(request);
        return ResponseEntity.ok(new MessageResponse("로그인 성공"));
    }



}
