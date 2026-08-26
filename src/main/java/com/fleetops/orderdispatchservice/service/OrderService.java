package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.order.OrderRequest;
import com.fleetops.orderdispatchservice.dto.order.OrderResponse;
import com.fleetops.orderdispatchservice.dto.order.OrderStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    Page<OrderResponse> getOrders(OrderStatus status, Pageable pageable);
    OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request);
}