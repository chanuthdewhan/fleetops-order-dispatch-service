package com.fleetops.orderdispatchservice.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String address;
}