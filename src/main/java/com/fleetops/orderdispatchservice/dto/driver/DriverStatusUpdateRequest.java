package com.fleetops.orderdispatchservice.dto.driver;

import com.fleetops.orderdispatchservice.enums.DriverStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DriverStatusUpdateRequest {
    @NotNull
    private DriverStatus status;
}