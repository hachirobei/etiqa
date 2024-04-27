package com.etiqa.onlinestore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import com.etiqa.onlinestore.entity.CustomerEntity;

public interface CustomerService {
    CustomerEntity createCustomer(CustomerEntity customer);
    Page<CustomerEntity> searchCustomers(String searchTerm, Pageable pageable);
    Optional<CustomerEntity> findById(Long id);
    CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails);
    CustomerEntity partialUpdateCustomer(Long id, CustomerEntity customerDetails);
    void deleteCustomer(Long id);
}

