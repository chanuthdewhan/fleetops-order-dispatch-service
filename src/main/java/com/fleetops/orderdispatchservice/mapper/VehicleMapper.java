package com.fleetops.orderdispatchservice.mapper;

import com.fleetops.orderdispatchservice.dto.vehicle.VehicleRequest;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleResponse;
import com.fleetops.orderdispatchservice.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toEntity(VehicleRequest request);
    VehicleResponse toResponse(VehicleRequest customer);
}
