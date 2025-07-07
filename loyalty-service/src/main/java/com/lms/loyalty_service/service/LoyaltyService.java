package com.lms.loyalty_service.service;

import com.lms.loyalty_service.dto.*;
import com.lms.loyalty_service.feign.UserClient;
import com.lms.loyalty_service.model.LoyaltyProfile;
import com.lms.loyalty_service.repository.LoyaltyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoyaltyService {

    private final LoyaltyRepository loyaltyRepository;
    private final UserClient userClient;

    public LoyaltyStatusDto enrollUser(EnrollRequestDto request) {
        Long userId = request.getUserId();

        if (!userClient.userExists(userId)) {
            throw new IllegalArgumentException("User ID does not exist");
        }

        if (loyaltyRepository.existsById(userId)) {
            throw new IllegalStateException("User already enrolled");
        }

        LoyaltyProfile profile = LoyaltyProfile.builder()
                .userId(userId)
                .tier("Silver")
                .points(0)
                .enrolledAt(LocalDateTime.now())
                .build();

        loyaltyRepository.save(profile);
        return mapToStatus(profile);
    }

    public LoyaltyStatusDto getLoyaltyStatus(Long userId) {
        LoyaltyProfile profile = loyaltyRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not enrolled"));

        return mapToStatus(profile);
    }

    public List<TierThresholdDto> getTierThresholds() {
        return List.of(
                new TierThresholdDto("Silver", 0, 999),
                new TierThresholdDto("Gold", 1000, 4999),
                new TierThresholdDto("Platinum", 5000, Integer.MAX_VALUE)
        );
    }

    private LoyaltyStatusDto mapToStatus(LoyaltyProfile profile) {
        return LoyaltyStatusDto.builder()
                .userId(profile.getUserId())
                .tier(profile.getTier())
                .points(profile.getPoints())
                .enrolledAt(profile.getEnrolledAt().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }

    public void addPointsForOrder(Long userId, double orderAmount) {
        LoyaltyProfile profile = loyaltyRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not enrolled"));

        int currentPoints = profile.getPoints();
        int pointsToAdd = calculatePoints(orderAmount);

        int updatedPoints = currentPoints + pointsToAdd;
        profile.setPoints(updatedPoints);

        // Update tier based on new points
        profile.setTier(determineTier(updatedPoints));

        loyaltyRepository.save(profile);
    }

    private int calculatePoints(double amount) {
        return (int)(amount * 0.1); // example: 10% of amount as points
    }

    private String determineTier(int totalPoints) {
        if (totalPoints >= 5000) return "Platinum";
        if (totalPoints >= 1000) return "Gold";
        return "Silver";
    }

}
