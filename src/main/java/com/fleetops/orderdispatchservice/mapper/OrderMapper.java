package com.fleetops.orderdispatchservice.mapper;

import com.fleetops.orderdispatchservice.dto.order.OrderRequest;
import com.fleetops.orderdispatchservice.dto.order.OrderResponse;
import com.fleetops.orderdispatchservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "customer", ignore = true)   // service layer sets this after fetching the real Customer
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Order toEntity(OrderRequest request);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    OrderResponse toResponse(Order order);
}
