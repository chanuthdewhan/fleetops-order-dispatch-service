package com.fleetops.orderdispatchservice.controller;

import com.fleetops.orderdispatchservice.dto.assignment.AssignmentRequest;
import com.fleetops.orderdispatchservice.dto.assignment.AssignmentResponse;
import com.fleetops.orderdispatchservice.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/{orderId}/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponse> assignDriverAndVehicle(
            @PathVariable Long orderId, @Valid @RequestBody AssignmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(assignmentService.assignDriverAndVehicle(orderId, request));
    }
}