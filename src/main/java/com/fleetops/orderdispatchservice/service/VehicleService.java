package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.vehicle.VehicleRequest;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleResponse;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleRequest request);
    VehicleResponse updateVehicleStatus(Long id, VehicleStatusUpdateRequest request);
    Page<VehicleResponse> getVehicles(VehicleStatus status, Pageable pageable);
}