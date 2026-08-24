package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.vehicle.VehicleRequest;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleResponse;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import java.util.List;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleRequest request);
    List<VehicleResponse> getVehicles(VehicleStatus status);
    VehicleResponse updateVehicleStatus(Long id, VehicleStatusUpdateRequest request);
}