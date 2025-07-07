package com.lms.loyalty_service.controller;

import com.lms.loyalty_service.dto.*;
import com.lms.loyalty_service.service.LoyaltyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyalty")
@RequiredArgsConstructor
public class LoyaltyController {

    private final LoyaltyService loyaltyService;

    @PostMapping("/enroll")
    public ResponseEntity<LoyaltyStatusDto> enroll(@Valid @RequestBody EnrollRequestDto request) {
        return ResponseEntity.ok(loyaltyService.enrollUser(request));
    }

    @GetMapping("/{userId}/status")
    public ResponseEntity<LoyaltyStatusDto> getStatus(@PathVariable Long userId) {
        return ResponseEntity.ok(loyaltyService.getLoyaltyStatus(userId));
    }

    @GetMapping("/tier-thresholds")
    public ResponseEntity<List<TierThresholdDto>> getThresholds() {
        return ResponseEntity.ok(loyaltyService.getTierThresholds());
    }
}

