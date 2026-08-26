package com.fleetops.orderdispatchservice.service;

import com.fleetops.orderdispatchservice.dto.customer.CustomerRequest;
import com.fleetops.orderdispatchservice.dto.customer.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    Page<CustomerResponse> getCustomers(Pageable pageable);
}