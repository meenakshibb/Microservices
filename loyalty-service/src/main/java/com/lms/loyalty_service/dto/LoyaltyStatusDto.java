package com.lms.loyalty_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyStatusDto {
    private Long userId;
    private String tier;
    private int points;
    private String enrolledAt;
}
