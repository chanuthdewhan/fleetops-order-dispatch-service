package com.fleetops.orderdispatchservice.dto.driver;

import com.fleetops.orderdispatchservice.enums.DriverStatus;
import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DriverResponse {
    private Long id;
    private String name;
    private String phone;
    private String licenseNumber;
    private DriverStatus status;
    private Instant createdAt;
}