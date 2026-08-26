package com.fleetops.orderdispatchservice.repository;

import com.fleetops.orderdispatchservice.entity.Vehicle;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Page<Vehicle> findByStatus(VehicleStatus status, Pageable pageable);
}