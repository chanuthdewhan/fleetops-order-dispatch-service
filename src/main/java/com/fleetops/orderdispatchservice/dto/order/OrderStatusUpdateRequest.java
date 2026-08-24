package com.fleetops.orderdispatchservice.dto.order;

import com.fleetops.orderdispatchservice.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderStatusUpdateRequest {
    @NotNull
    private OrderStatus status;
}