package com.fleetops.orderdispatchservice.exception;

public class ResourceUnavailableException extends RuntimeException {
    public ResourceUnavailableException(String message) {
        super(message);
    }
}
