package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.assignment.AssignmentRequest;
import com.fleetops.orderdispatchservice.dto.assignment.AssignmentResponse;

public interface AssignmentService {
    AssignmentResponse assignDriverAndVehicle(Long orderId, AssignmentRequest request);
}