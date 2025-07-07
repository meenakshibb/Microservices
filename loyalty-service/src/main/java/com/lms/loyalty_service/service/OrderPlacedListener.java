package com.lms.loyalty_service.service;

import com.lms.loyalty_service.events.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPlacedListener {

    private final LoyaltyService loyaltyService;

    @KafkaListener(topics = "order-placed", groupId = "loyalty-service")
    public void onOrderPlaced(OrderPlacedEvent event) {

        loyaltyService.addPointsForOrder(event.getUserId(), event.getTotalAmount());
    }
}
