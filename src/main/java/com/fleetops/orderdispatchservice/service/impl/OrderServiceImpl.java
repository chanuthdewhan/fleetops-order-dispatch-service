package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.order.OrderRequest;
import com.fleetops.orderdispatchservice.dto.order.OrderResponse;
import com.fleetops.orderdispatchservice.dto.order.OrderStatusUpdateRequest;
import com.fleetops.orderdispatchservice.entity.Customer;
import com.fleetops.orderdispatchservice.entity.Order;
import com.fleetops.orderdispatchservice.enums.OrderStatus;
import com.fleetops.orderdispatchservice.exception.InvalidStateTransitionException;
import com.fleetops.orderdispatchservice.exception.ResourceNotFoundException;
import com.fleetops.orderdispatchservice.mapper.OrderMapper;
import com.fleetops.orderdispatchservice.repository.CustomerRepository;
import com.fleetops.orderdispatchservice.repository.OrderRepository;
import com.fleetops.orderdispatchservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    // The state machine, made explicit and readable in one place —
    // not scattered as scattered if-checks across the codebase.
    private static final Map<OrderStatus, Set<OrderStatus>> ALLOWED_TRANSITIONS = Map.of(
            OrderStatus.PENDING, Set.of(OrderStatus.ASSIGNED, OrderStatus.CANCELLED),
            OrderStatus.ASSIGNED, Set.of(OrderStatus.IN_TRANSIT, OrderStatus.CANCELLED),
            OrderStatus.IN_TRANSIT, Set.of(OrderStatus.DELIVERED),
            OrderStatus.DELIVERED, Set.of(),
            OrderStatus.CANCELLED, Set.of()
    );

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + request.getCustomerId()));

        Order order = orderMapper.toEntity(request);
        order.setCustomer(customer);

        Order saved = orderRepository.save(order);
        log.info("Order created: id={}, customerId={}", saved.getId(), customer.getId());
        return orderMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        return orderMapper.toResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponse> getOrders(OrderStatus status, Pageable pageable) {
        Page<Order> page = (status != null)
                ? orderRepository.findByStatus(status, pageable)
                : orderRepository.findAll(pageable);
        return page.map(orderMapper::toResponse);
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long id, OrderStatusUpdateRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));

        OrderStatus current = order.getStatus();
        OrderStatus target = request.getStatus();

        if (!ALLOWED_TRANSITIONS.get(current).contains(target)) {
            throw new InvalidStateTransitionException(
                    "Cannot transition order from " + current + " to " + target);
        }

        order.setStatus(target);
        log.info("Order {} status transitioned {} -> {}", id, current, target);
        return orderMapper.toResponse(order);
    }
}