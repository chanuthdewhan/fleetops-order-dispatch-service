package com.fleetops.orderdispatchservice.controller;

import com.fleetops.orderdispatchservice.dto.driver.DriverRequest;
import com.fleetops.orderdispatchservice.dto.driver.DriverResponse;
import com.fleetops.orderdispatchservice.dto.driver.DriverStatusUpdateRequest;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import com.fleetops.orderdispatchservice.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(request));
    }

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getDrivers(
            @RequestParam(required = false) DriverStatus status) {
        return ResponseEntity.ok(driverService.getDrivers(status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DriverResponse> updateDriverStatus(
            @PathVariable Long id, @Valid @RequestBody DriverStatusUpdateRequest request) {
        return ResponseEntity.ok(driverService.updateDriverStatus(id, request));
    }
}