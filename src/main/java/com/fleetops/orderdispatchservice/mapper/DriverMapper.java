package com.fleetops.orderdispatchservice.mapper;

import com.fleetops.orderdispatchservice.dto.driver.DriverRequest;
import com.fleetops.orderdispatchservice.dto.driver.DriverResponse;
import com.fleetops.orderdispatchservice.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toEntity(DriverRequest request);
    DriverResponse toResponse(Driver driver);
}
