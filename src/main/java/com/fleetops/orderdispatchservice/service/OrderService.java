package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.order.OrderRequest;
import com.fleetops.orderdispatchservice.dto.order.OrderResponse;
import com.fleetops.orderdispatchservice.dto.order.OrderStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.OrderStatus;
import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getOrders(OrderStatus status);
    OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request);
}