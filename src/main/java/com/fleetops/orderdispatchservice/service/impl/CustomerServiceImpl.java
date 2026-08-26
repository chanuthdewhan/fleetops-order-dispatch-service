package com.fleetops.orderdispatchservice.service.impl;

import com.fleetops.orderdispatchservice.dto.customer.CustomerRequest;
import com.fleetops.orderdispatchservice.dto.customer.CustomerResponse;
import com.fleetops.orderdispatchservice.entity.Customer;
import com.fleetops.orderdispatchservice.exception.ResourceNotFoundException;
import com.fleetops.orderdispatchservice.mapper.CustomerMapper;
import com.fleetops.orderdispatchservice.repository.CustomerRepository;
import com.fleetops.orderdispatchservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        log.info("Customer created: id={}", saved.getId());
        return customerMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponse> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customerMapper::toResponse);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        log.info("Customer updated: id={}", id);
        return customerMapper.toResponse(customer);
    }

}