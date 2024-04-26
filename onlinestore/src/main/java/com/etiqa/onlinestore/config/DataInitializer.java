package com.etiqa.onlinestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etiqa.onlinestore.entity.ProductEntity;
import com.etiqa.onlinestore.entity.CustomerEntity;
import com.etiqa.onlinestore.service.CustomerService;
import com.etiqa.onlinestore.service.ProductService;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final ProductService productService;
    private final CustomerService customerService;

    @Autowired
    public DataInitializer(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @PostConstruct
    public void initializeData() {
        initializeProducts();
        initializeCustomers();
    }

    private void initializeProducts() {
        List<ProductEntity> products = Arrays.asList(
            new ProductEntity("Laptop", 1299.99, 20),
            new ProductEntity("Smartphone", 799.99, 50),
            new ProductEntity("Tablet", 499.99, 30),
            new ProductEntity("Headphones", 199.99, 100),
            new ProductEntity("Smartwatch", 299.99, 80),
            new ProductEntity("Wireless Earbuds", 129.99, 120),
            new ProductEntity("Camera", 899.99, 40),
            new ProductEntity("Fitness Tracker", 79.99, 150),
            new ProductEntity("Gaming Console", 399.99, 60),
            new ProductEntity("TV", 1499.99, 25)
        );
        products.forEach(productService::createProduct);
        
    }

    private void initializeCustomers() {
        List<CustomerEntity> customers = Arrays.asList(
            new CustomerEntity("John", "Doe", "john.doe@example.com", "personal@gmail.com", 3),
            new CustomerEntity("Alice", "Smith", "alice.smith@example.com", "alice@hotmail.com", 2),
            new CustomerEntity("Michael", "Johnson", "michael.johnson@example.com", "mike@example.com", 4),
            new CustomerEntity("Emily", "Brown", "emily.brown@example.com", "emily.brown@example.com", 1),
            new CustomerEntity("Robert", "Wilson", "robert.wilson@example.com", "rob@example.com", 5),
            new CustomerEntity("Sophia", "Martinez", "sophia.martinez@example.com", "sophia.m@example.com", 2),
            new CustomerEntity("William", "Taylor", "william.taylor@example.com", "will.taylor@example.com", 3),
            new CustomerEntity("Emma", "Anderson", "emma.anderson@example.com", "emma.anderson@example.com", 2),
            new CustomerEntity("James", "Thomas", "james.thomas@example.com", "j.thomas@example.com", 6),
            new CustomerEntity("Olivia", "Garcia", "olivia.garcia@example.com", "olivia.garcia@example.com", 2),
            new CustomerEntity("David", "Hernandez", "david.hernandez@example.com", "david.h@example.com", 4),
            new CustomerEntity("Isabella", "Young", "isabella.young@example.com", "isabella.young@example.com", 3),
            new CustomerEntity("Ethan", "Lee", "ethan.lee@example.com", "ethan.lee@example.com", 1),
            new CustomerEntity("Charlotte", "Lopez", "charlotte.lopez@example.com", "charlotte.lopez@example.com", 2),
            new CustomerEntity("Alexander", "King", "alexander.king@example.com", "alex.k@example.com", 5),
            new CustomerEntity("Mia", "Wright", "mia.wright@example.com", "mia.w@example.com", 3),
            new CustomerEntity("Daniel", "Scott", "daniel.scott@example.com", "d.scott@example.com", 2),
            new CustomerEntity("Ava", "Green", "ava.green@example.com", "ava.g@example.com", 1),
            new CustomerEntity("Logan", "Adams", "logan.adams@example.com", "logan.adams@example.com", 4),
            new CustomerEntity("Evelyn", "Hall", "evelyn.hall@example.com", "evelyn.hall@example.com", 2)
        );
        customers.forEach(customerService::createCustomer); 
    }
}
