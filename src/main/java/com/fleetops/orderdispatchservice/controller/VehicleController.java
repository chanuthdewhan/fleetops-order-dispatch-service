package com.fleetops.orderdispatchservice.controller;

import com.fleetops.orderdispatchservice.dto.vehicle.VehicleRequest;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleResponse;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import com.fleetops.orderdispatchservice.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody VehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(request));
    }

    @GetMapping
    public ResponseEntity<Page<VehicleResponse>> getVehicles(
            @RequestParam(required = false) VehicleStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(vehicleService.getVehicles(status, PageRequest.of(page, size)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<VehicleResponse> updateVehicleStatus(
            @PathVariable Long id, @Valid @RequestBody VehicleStatusUpdateRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicleStatus(id, request));
    }

}