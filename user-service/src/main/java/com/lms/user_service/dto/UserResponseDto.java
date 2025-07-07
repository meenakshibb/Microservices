package com.lms.user_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String createdAt;
}

