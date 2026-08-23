// entity/Assignment.java
package com.fleetops.orderdispatchservice.entity;

import com.fleetops.orderdispatchservice.enums.AssignmentStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false, updatable = false)
    private Instant assignedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    @PrePersist
    void onCreate() {
        this.assignedAt = Instant.now();
        if (this.status == null) this.status = AssignmentStatus.ASSIGNED;
    }

}