package com.lms.loyalty_service.repository;

import com.lms.loyalty_service.model.LoyaltyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyRepository extends JpaRepository<LoyaltyProfile, Long> {
}

