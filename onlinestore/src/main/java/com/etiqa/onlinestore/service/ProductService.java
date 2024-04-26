package com.etiqa.onlinestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.etiqa.onlinestore.entity.ProductEntity;
import com.etiqa.onlinestore.exception.ResourceNotFoundException;
import com.etiqa.onlinestore.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
	@Autowired
    private final ProductRepository productRepository = null;

    // Create a new product
    @Transactional
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    // Retrieve all products with search and pagination
    @Transactional(readOnly = true)
    public Page<ProductEntity> searchProducts(String searchTerm, Pageable pageable) {
        return productRepository.findBySearchTerm(searchTerm, pageable);
    }

    // Retrieve a product by ID
    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    // Update a product completely
    @Transactional
    public ProductEntity updateProduct(Long id, ProductEntity productDetails) {
        ProductEntity product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        product.setBookTitle(productDetails.getBookTitle());
        product.setBookPrice(productDetails.getBookPrice());
        product.setBookQuantity(productDetails.getBookQuantity());

        return productRepository.save(product);
    }
    
    // Partially update a product
    @Transactional
    public ProductEntity partialUpdateProduct(Long id, ProductEntity productDetails) {
        ProductEntity product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        if (productDetails.getBookTitle() != null) product.setBookTitle(productDetails.getBookTitle());
        if (productDetails.getBookPrice() != null) product.setBookPrice(productDetails.getBookPrice());
        if (productDetails.getBookQuantity() != null) product.setBookQuantity(productDetails.getBookQuantity());

        return productRepository.save(product);
    }
    
    // Delete a product
    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productRepository.delete(product);
    }
}
