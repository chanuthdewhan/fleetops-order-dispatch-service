package com.fleetops.orderdispatchservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING, ASSIGNED, IN_TRANSIT, DELIVERED, CANCELLED
}
