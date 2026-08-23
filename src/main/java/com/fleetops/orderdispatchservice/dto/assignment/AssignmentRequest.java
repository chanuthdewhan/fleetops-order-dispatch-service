package com.fleetops.orderdispatchservice.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssignmentRequest {
    @NotNull
    private Long driverId;
    @NotNull
    private Long vehicleId;
}