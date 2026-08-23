package com.fleetops.orderdispatchservice.dto.order;

import com.fleetops.orderdispatchservice.enums.OrderStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Long id;
    private Long customerId;
    private String customerName;
    private String pickupAddress;
    private String dropoffAddress;
    private BigDecimal weightKg;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}