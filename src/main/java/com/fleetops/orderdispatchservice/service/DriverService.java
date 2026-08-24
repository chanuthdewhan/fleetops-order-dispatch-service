package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.driver.DriverRequest;
import com.fleetops.orderdispatchservice.dto.driver.DriverResponse;
import com.fleetops.orderdispatchservice.dto.driver.DriverStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import java.util.List;

public interface DriverService {
    DriverResponse createDriver(DriverRequest request);
    List<DriverResponse> getDrivers(DriverStatus status);
    DriverResponse updateDriverStatus(Long id, DriverStatusUpdateRequest request);
}