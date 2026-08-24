package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.customer.CustomerRequest;
import com.fleetops.orderdispatchservice.dto.customer.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
}