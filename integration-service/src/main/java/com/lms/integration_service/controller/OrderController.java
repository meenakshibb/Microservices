package com.lms.integration_service.controller;

import events.OrderPlacedEvent;
import com.lms.integration_service.service.KafkaPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private KafkaPublisherService kafkaPublisher;

    @PostMapping
    public ResponseEntity<String> publishOrder(@RequestBody OrderPlacedEvent event) {
        kafkaPublisher.sendOrderPlaced(event);
        return ResponseEntity.ok("Order event published successfully.");
    }
}

