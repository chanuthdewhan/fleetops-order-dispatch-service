package com.fleetops.orderdispatchservice.dto.vehicle;

import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import com.fleetops.orderdispatchservice.enums.VehicleType;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleResponse {
    private Long id;
    private String plateNumber;
    private VehicleType vehicleType;
    private BigDecimal capacityKg;
    private VehicleStatus status;
    private Instant createdAt;
}