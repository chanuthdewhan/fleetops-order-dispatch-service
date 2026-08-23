package com.fleetops.orderdispatchservice.repository;

import com.fleetops.orderdispatchservice.entity.Driver;
import com.fleetops.orderdispatchservice.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByStatus(DriverStatus status);
}