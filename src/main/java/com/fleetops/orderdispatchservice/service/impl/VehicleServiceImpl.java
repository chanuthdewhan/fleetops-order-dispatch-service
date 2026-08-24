package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.vehicle.VehicleRequest;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleResponse;
import com.fleetops.orderdispatchservice.dto.vehicle.VehicleStatusUpdateRequest;
import com.fleetops.orderdispatchservice.entity.Vehicle;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import com.fleetops.orderdispatchservice.exception.ResourceNotFoundException;
import com.fleetops.orderdispatchservice.mapper.VehicleMapper;
import com.fleetops.orderdispatchservice.repository.VehicleRepository;
import com.fleetops.orderdispatchservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    @Transactional
    public VehicleResponse createVehicle(VehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toEntity(request);
        Vehicle saved = vehicleRepository.save(vehicle);
        log.info("Vehicle created: id={}", saved.getId());
        return vehicleMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> getVehicles(VehicleStatus status) {
        List<Vehicle> vehicles = (status != null)
                ? vehicleRepository.findByStatus(status)
                : vehicleRepository.findAll();
        return vehicles.stream().map(vehicleMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public VehicleResponse updateVehicleStatus(Long id, VehicleStatusUpdateRequest request) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found: " + id));
        vehicle.setStatus(request.getStatus());
        log.info("Vehicle {} status updated to {}", id, request.getStatus());
        return vehicleMapper.toResponse(vehicle);
    }

}