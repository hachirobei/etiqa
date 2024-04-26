package com.etiqa.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etiqa.onlinestore.entity.ProductEntity;
import com.etiqa.onlinestore.exception.ResourceNotFoundException;
import com.etiqa.onlinestore.dto.ApiResponse;
import com.etiqa.onlinestore.dto.PaginatedResponse;
import com.etiqa.onlinestore.dto.PaginationMeta;
import com.etiqa.onlinestore.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST: Create a new product
    @PostMapping
    public ResponseEntity<ApiResponse<ProductEntity>> createProduct(@RequestBody ProductEntity product) {
        ProductEntity savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(savedProduct));
    }

    // GET: Retrieve all products with pagination and search
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductEntity>>> searchProducts(
        @RequestParam(value = "search", defaultValue = "") String searchTerm,
        Pageable pageable) {
        Page<ProductEntity> products = productService.searchProducts(searchTerm, pageable);
        PaginationMeta meta = new PaginationMeta(pageable.getPageNumber(), pageable.getPageSize(), products.getTotalElements(), products.getTotalPages());
        return ResponseEntity.ok(PaginatedResponse.success(products, meta));
    }

    // GET: Retrieve a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductEntity>> getProductById(@PathVariable Long id) {
        ProductEntity product = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    // PUT: Update a product entirely
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductEntity>> updateProduct(@PathVariable Long id, @RequestBody ProductEntity productDetails) {
        ProductEntity updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(ApiResponse.success(updatedProduct));
    }
    
    // PATCH: Update part of a product
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductEntity>> partialUpdateProduct(@PathVariable Long id, @RequestBody ProductEntity productDetails) {
    	ProductEntity updatedProduct = productService.partialUpdateProduct(id, productDetails);
        return ResponseEntity.ok(ApiResponse.success(updatedProduct));
    }


    // DELETE: Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
