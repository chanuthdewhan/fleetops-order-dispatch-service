package com.fleetops.orderdispatchservice.repository;

import com.fleetops.orderdispatchservice.entity.Assignment;
import com.fleetops.orderdispatchservice.enums.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByOrderIdAndStatus(Long orderId, AssignmentStatus status);
}