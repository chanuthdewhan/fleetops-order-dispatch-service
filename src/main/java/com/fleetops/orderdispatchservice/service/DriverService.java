package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.driver.DriverRequest;
import com.fleetops.orderdispatchservice.dto.driver.DriverResponse;
import com.fleetops.orderdispatchservice.dto.driver.DriverStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    DriverResponse createDriver(DriverRequest request);
    DriverResponse updateDriverStatus(Long id, DriverStatusUpdateRequest request);
    Page<DriverResponse> getDrivers(DriverStatus status, Pageable pageable);
}