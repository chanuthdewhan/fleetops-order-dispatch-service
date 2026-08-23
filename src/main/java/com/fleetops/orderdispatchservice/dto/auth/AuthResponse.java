package com.fleetops.orderdispatchservice.dto.auth;

import com.fleetops.orderdispatchservice.enums.UserRole;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    private String username;
    private UserRole role;
}