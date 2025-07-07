package com.lms.integration_service.service;

import com.lms.integration_service.events.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    private static final String TOPIC = "order-placed";

    public void sendOrderPlaced(OrderPlacedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
