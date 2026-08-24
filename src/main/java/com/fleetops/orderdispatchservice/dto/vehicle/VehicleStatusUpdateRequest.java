package com.fleetops.orderdispatchservice.dto.vehicle;

import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleStatusUpdateRequest {
    @NotNull
    private VehicleStatus status;
}