package com.example.demo.dto.auth;

import com.example.demo.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class UserSaveRequestDto {
    private String userId;   //stu250307
    private String name;
    private String phone;
    private String password;
    private UserRole userRole;
    private Integer grade;
    private Integer classNum;
    private Integer number;



}
