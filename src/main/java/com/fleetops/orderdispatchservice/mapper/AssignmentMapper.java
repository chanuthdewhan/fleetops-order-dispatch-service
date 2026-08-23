package com.fleetops.orderdispatchservice.mapper;

import com.fleetops.orderdispatchservice.dto.assignment.AssignmentResponse;
import com.fleetops.orderdispatchservice.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "driver.name", target = "driverName")
    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "vehicle.plateNumber", target = "plateNumber")
    AssignmentResponse toResponse(Assignment assignment);
}
