package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.auth.AuthResponse;
import com.fleetops.orderdispatchservice.dto.auth.LoginRequest;
import com.fleetops.orderdispatchservice.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}