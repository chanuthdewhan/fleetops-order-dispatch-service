package com.fleetops.orderdispatchservice.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    @NotNull
    private Long customerId;
    @NotBlank
    private String pickupAddress;
    @NotBlank
    private String dropoffAddress;
    @NotNull @Positive
    private BigDecimal weightKg;
}