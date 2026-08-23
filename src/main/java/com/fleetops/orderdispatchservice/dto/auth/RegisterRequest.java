package com.fleetops.orderdispatchservice.dto.auth;

import com.fleetops.orderdispatchservice.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank @Size(min = 6)
    private String password;
    @NotNull
    private UserRole role;
}