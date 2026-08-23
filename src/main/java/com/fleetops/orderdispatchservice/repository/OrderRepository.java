package com.fleetops.orderdispatchservice.repository;

import com.fleetops.orderdispatchservice.entity.Order;
import com.fleetops.orderdispatchservice.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}