package com.fleetops.orderdispatchservice.mapper;

import com.fleetops.orderdispatchservice.dto.customer.CustomerRequest;
import com.fleetops.orderdispatchservice.dto.customer.CustomerResponse;
import com.fleetops.orderdispatchservice.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer customer);
}
