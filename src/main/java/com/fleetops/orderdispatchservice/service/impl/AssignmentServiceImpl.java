package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.assignment.AssignmentRequest;
import com.fleetops.orderdispatchservice.dto.assignment.AssignmentResponse;
import com.fleetops.orderdispatchservice.entity.*;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import com.fleetops.orderdispatchservice.enums.OrderStatus;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import com.fleetops.orderdispatchservice.enums.AssignmentStatus;
import com.fleetops.orderdispatchservice.exception.InvalidStateTransitionException;
import com.fleetops.orderdispatchservice.exception.ResourceNotFoundException;
import com.fleetops.orderdispatchservice.exception.ResourceUnavailableException;
import com.fleetops.orderdispatchservice.mapper.AssignmentMapper;
import com.fleetops.orderdispatchservice.repository.*;
import com.fleetops.orderdispatchservice.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    @Transactional
    public AssignmentResponse assignDriverAndVehicle(Long orderId, AssignmentRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidStateTransitionException(
                    "Order " + orderId + " is not PENDING, cannot assign (current: " + order.getStatus() + ")");
        }

        Driver driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + request.getDriverId()));

        if (driver.getStatus() != DriverStatus.AVAILABLE) {
            throw new ResourceUnavailableException(
                    "Driver " + driver.getId() + " is not AVAILABLE (current: " + driver.getStatus() + ")");
        }

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + request.getVehicleId()));

        if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new ResourceUnavailableException(
                    "Vehicle " + vehicle.getId() + " is not AVAILABLE (current: " + vehicle.getStatus() + ")");
        }

        // All checks passed — perform the actual business transaction:
        // create the assignment, and update all three related entities' state together.
        Assignment assignment = Assignment.builder()
                .order(order)
                .driver(driver)
                .vehicle(vehicle)
                .build();
        Assignment saved = assignmentRepository.save(assignment);

        order.setStatus(OrderStatus.ASSIGNED);
        driver.setStatus(DriverStatus.ON_TRIP);
        vehicle.setStatus(VehicleStatus.ON_TRIP);

        log.info("Assignment created: orderId={}, driverId={}, vehicleId={}",
                orderId, driver.getId(), vehicle.getId());

        return assignmentMapper.toResponse(saved);
    }
}