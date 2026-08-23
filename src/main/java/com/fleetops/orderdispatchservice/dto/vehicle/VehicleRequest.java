package com.fleetops.orderdispatchservice.dto.vehicle;

import com.fleetops.orderdispatchservice.enums.VehicleType;
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
public class VehicleRequest {
    @NotBlank
    private String plateNumber;
    @NotNull
    private VehicleType vehicleType;
    @NotNull @Positive
    private BigDecimal capacityKg;
}