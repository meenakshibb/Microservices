package com.lms.loyalty_service.service;

import events.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPlacedListener {

    private final LoyaltyService loyaltyService;

    @KafkaListener(
            topics = "order-placed",
            groupId = "loyalty-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
   /* public void onMessage(String rawMessage) {
        System.out.println("RAW: " + rawMessage);
    }*/
    public void onOrderPlaced(OrderPlacedEvent event) {
        loyaltyService.addPointsForOrder(event.getUserId(), event.getTotalAmount());
    }

}
