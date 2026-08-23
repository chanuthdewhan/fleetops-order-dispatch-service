package com.fleetops.orderdispatchservice.dto.assignment;

import com.fleetops.orderdispatchservice.enums.AssignmentStatus;
import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssignmentResponse {
    private Long id;
    private Long orderId;
    private Long driverId;
    private String driverName;
    private Long vehicleId;
    private String plateNumber;
    private Instant assignedAt;
    private AssignmentStatus status;
}