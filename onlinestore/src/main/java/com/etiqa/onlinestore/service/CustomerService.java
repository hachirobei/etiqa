package com.etiqa.onlinestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.etiqa.onlinestore.entity.CustomerEntity;
import com.etiqa.onlinestore.exception.ResourceNotFoundException;
import com.etiqa.onlinestore.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
	@Autowired
    private final CustomerRepository customerRepository = null;

    // Create a new customer
    @Transactional
    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    // Retrieve all customers with search and pagination
    @Transactional(readOnly = true)
    public Page<CustomerEntity> searchCustomers(String searchTerm, Pageable pageable) {
        return customerRepository.findBySearchTerm(searchTerm, pageable);
    }

    // Retrieve a customer by ID
    @Transactional(readOnly = true)
    public Optional<CustomerEntity> findById(Long id) {
        return customerRepository.findById(id);
    }

    // Update a customer completely
    @Transactional
    public CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails) {
        CustomerEntity customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        customer.setFirstName(customerDetails.getFirstName()); 
        customer.setLastName(customerDetails.getLastName());
        customer.setEmailOffice(customerDetails.getEmailOffice());
        customer.setEmailPersonal(customerDetails.getEmailPersonal());
        customer.setFamilyMembers(customerDetails.getFamilyMembers());
        
        return customerRepository.save(customer);
    }
    
    // Partially update a customer
    @Transactional
    public CustomerEntity partialUpdateCustomer(Long id, CustomerEntity customerDetails) {
        CustomerEntity customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));

        if (customerDetails.getFirstName() != null) customer.setFirstName(customerDetails.getFirstName());
        if (customerDetails.getLastName() != null) customer.setLastName(customerDetails.getLastName());
        if (customerDetails.getEmailOffice() != null) customer.setEmailOffice(customerDetails.getEmailOffice());
        if (customerDetails.getEmailPersonal() != null) customer.setEmailPersonal(customerDetails.getEmailPersonal());
        if (customerDetails.getFamilyMembers() != null) customer.setFamilyMembers(customerDetails.getFamilyMembers());

        return customerRepository.save(customer);
    }

    // Delete a customer
    @Transactional
    public void deleteCustomer(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
        customerRepository.delete(customer);
    }
}
