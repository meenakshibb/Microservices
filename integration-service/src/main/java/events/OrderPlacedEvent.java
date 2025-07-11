package events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
    private String orderId;
    private Long userId;
    private double totalAmount;
    private String currency;
    //private LocalDateTime timestamp;

    // Getters and Setters
}

