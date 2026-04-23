package com.example.demo.service;


import com.example.demo.dto.auth.LoginRequest;
import com.example.demo.dto.auth.UserInfoResponse;
import com.example.demo.dto.auth.UserSaveRequestDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public void doLogin(LoginRequest dto) {
        //유저 있는지 없는지
        User user = userRepository.findByUserId(dto.getUserId()).orElseThrow(UserNotFoundException::new);

        if(!user.isPasswordEncoded()) {
            if(!user.getPassword().equals(dto.getPassword()))
                throw new RuntimeException("비밀번호 틀림");


            String encoded = passwordEncoder.encode(dto.getPassword()); // 비번 해쉬하기

            user.setPassword(encoded);      //해쉬된 비번 저장
            user.setPasswordEncoded(true);  //비번 해쉬된거 true

            userRepository.save(user);  //저장

        } else {
            if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {   //비번 암호화된 사용자 비번 검증
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        }


        UserInfoResponse.from(user);
    }
}
