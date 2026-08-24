package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.driver.DriverRequest;
import com.fleetops.orderdispatchservice.dto.driver.DriverResponse;
import com.fleetops.orderdispatchservice.dto.driver.DriverStatusUpdateRequest;
import com.fleetops.orderdispatchservice.entity.Driver;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import com.fleetops.orderdispatchservice.exception.ResourceNotFoundException;
import com.fleetops.orderdispatchservice.mapper.DriverMapper;
import com.fleetops.orderdispatchservice.repository.DriverRepository;
import com.fleetops.orderdispatchservice.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    @Transactional
    public DriverResponse createDriver(DriverRequest request) {
        Driver driver = driverMapper.toEntity(request);
        Driver saved = driverRepository.save(driver);
        log.info("Driver created: id={}", saved.getId());
        return driverMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverResponse> getDrivers(DriverStatus status) {
        List<Driver> drivers = (status != null)
                ? driverRepository.findByStatus(status)
                : driverRepository.findAll();
        return drivers.stream().map(driverMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public DriverResponse updateDriverStatus(Long id, DriverStatusUpdateRequest request) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + id));
        driver.setStatus(request.getStatus());
        log.info("Driver {} status updated to {}", id, request.getStatus());
        return driverMapper.toResponse(driver);
    }
}