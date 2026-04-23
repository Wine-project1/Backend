package com.example.demo.dto.auth;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private String userId;   //stu250307
    private String name;
    private String phone;
    private Integer grade;
    private Integer classNum;
    private Integer number;
    private Boolean isActive;
    private LocalDateTime createdAt;




    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}
