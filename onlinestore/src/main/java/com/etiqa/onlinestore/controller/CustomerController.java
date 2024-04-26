package com.etiqa.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etiqa.onlinestore.entity.CustomerEntity;
import com.etiqa.onlinestore.exception.ResourceNotFoundException;
import com.etiqa.onlinestore.dto.ApiResponse;
import com.etiqa.onlinestore.dto.PaginatedResponse;
import com.etiqa.onlinestore.dto.PaginationMeta;
import com.etiqa.onlinestore.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST: Create a new customer
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> createCustomer(@RequestBody CustomerEntity customer) {
        CustomerEntity savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(savedCustomer));
    }

    // GET: Retrieve all customers with pagination and search
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerEntity>>> searchCustomers(
        @RequestParam(value = "search", defaultValue = "") String searchTerm,
        Pageable pageable) {
        Page<CustomerEntity> customers = customerService.searchCustomers(searchTerm, pageable);
        PaginationMeta meta = new PaginationMeta(pageable.getPageNumber(), pageable.getPageSize(), customers.getTotalElements(), customers.getTotalPages());
        return ResponseEntity.ok(PaginatedResponse.success(customers, meta));
    }

    // GET: Retrieve a single customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerEntity>> getCustomerById(@PathVariable Long id) {
        CustomerEntity customer = customerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        return ResponseEntity.ok(ApiResponse.success(customer));
    }

    // PUT: Update a customer entirely
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerEntity>> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
        CustomerEntity updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return ResponseEntity.ok(ApiResponse.success(updatedCustomer));
    }

    // PATCH: Update part of a customer
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerEntity>> partialUpdateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
        CustomerEntity updatedCustomer = customerService.partialUpdateCustomer(id, customerDetails);
        return ResponseEntity.ok(ApiResponse.success(updatedCustomer));
    }

    // DELETE: Delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}