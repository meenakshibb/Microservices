package com.lms.loyalty_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TierThresholdDto {
    private String tierName;
    private int minPoints;
    private int maxPoints;
}
