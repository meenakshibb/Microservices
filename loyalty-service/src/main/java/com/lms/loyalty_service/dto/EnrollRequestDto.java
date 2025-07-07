package com.lms.loyalty_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollRequestDto {
    @NotNull
    private Long userId;
}
