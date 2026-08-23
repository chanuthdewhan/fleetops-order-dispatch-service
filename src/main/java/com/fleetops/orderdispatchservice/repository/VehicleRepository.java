package com.fleetops.orderdispatchservice.repository;

import com.fleetops.orderdispatchservice.entity.Vehicle;
import com.fleetops.orderdispatchservice.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByStatus(VehicleStatus status);
}