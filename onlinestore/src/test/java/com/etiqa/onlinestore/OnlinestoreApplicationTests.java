package com.etiqa.onlinestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.etiqa.onlinestore.controller.CustomerController;
import com.etiqa.onlinestore.controller.ProductController;
import com.etiqa.onlinestore.entity.CustomerEntity;
import com.etiqa.onlinestore.entity.ProductEntity;
import com.etiqa.onlinestore.service.CustomerService;
import com.etiqa.onlinestore.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(controllers = {CustomerController.class, ProductController.class})
public class OnlinestoreApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;
    
    @MockBean
    private ProductService productService;

    @TestConfiguration
    static class AdditionalConfig {
        // Define any additional beans or configurations only for testing here
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

    
    // Test for Customer
    @Test
    public void createCustomerTest() throws Exception {
        CustomerEntity newCustomer = new CustomerEntity("Alice", "Smith", "alice@example.com", "alice.secondary@example.com", 3);
        when(customerService.createCustomer(any(CustomerEntity.class))).thenReturn(newCustomer);

        mvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newCustomer)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.firstName").value("Alice"));
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        CustomerEntity customer = new CustomerEntity("John", "Doe", "john.doe@example.com", "john.secondary@example.com", 4);
        when(customerService.findById(any(Long.class))).thenReturn(Optional.of(customer));

        mvc.perform(get("/api/customers/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.firstName").value("John"));
    }

    @Test
    public void searchCustomersTest() throws Exception {
        Page<CustomerEntity> page = new PageImpl<>(Arrays.asList(
            new CustomerEntity("John", "Doe", "john.doe@example.com", "john.secondary@example.com", 4),
            new CustomerEntity("Alice", "Smith", "alice@example.com", "alice.secondary@example.com", 3)
        ), PageRequest.of(0, 2), 2);
        when(customerService.searchCustomers(anyString(), any())).thenReturn(page);

        mvc.perform(get("/api/customers?search=John&page=0&size=2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].firstName").value("John"));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        CustomerEntity updatedCustomer = new CustomerEntity("Alice", "Johnson", "alice@example.com", "alice.secondary@example.com", 3);
        when(customerService.updateCustomer(eq(1L), any(CustomerEntity.class))).thenReturn(updatedCustomer);

        mvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.firstName").value("Alice"));
    }

    @Test
    public void partialUpdateCustomerTest() throws Exception {
        CustomerEntity updatedCustomer = new CustomerEntity("Alice", "Johnson", "alice@example.com", "alice.secondary@example.com", 3);
        when(customerService.partialUpdateCustomer(eq(1L), any(CustomerEntity.class))).thenReturn(updatedCustomer);

        mvc.perform(patch("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.lastName").value("Johnson"));
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        doNothing().when(customerService).deleteCustomer(any(Long.class));

        mvc.perform(delete("/api/customers/1"))
            .andExpect(status().isOk());
    }
    
    // Test for Product
    @Test
    public void createProductTest() throws Exception {
        ProductEntity newProduct = new ProductEntity("Laptop", 1500.00, 2);
        when(productService.createProduct(any(ProductEntity.class))).thenReturn(newProduct);

        mvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newProduct)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.bookTitle").value("Laptop"));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        ProductEntity product = new ProductEntity("Smartphone", 700.00, 4);
        when(productService.findById(any(Long.class))).thenReturn(Optional.of(product));

        mvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.bookTitle").value("Smartphone"));
    }
    

    @Test
    public void searchProductsTest() throws Exception {
        Page<ProductEntity> page = new PageImpl<>(Arrays.asList(
            new ProductEntity("Tablet", 600.00, 2),
            new ProductEntity("Earbuds", 200.00, 3)
        ), PageRequest.of(0, 2), 2);
        when(productService.searchProducts(anyString(), any())).thenReturn(page);

        mvc.perform(get("/api/products?search=tablet&page=0&size=2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.content[0].bookTitle").value("Tablet"));
    }
}
