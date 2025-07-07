package com.lms.loyalty_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyProfile {

    @Id
    private Long userId;

    private String tier; // e.g., Silver, Gold, Platinum
    private int points;

    private LocalDateTime enrolledAt;
}

