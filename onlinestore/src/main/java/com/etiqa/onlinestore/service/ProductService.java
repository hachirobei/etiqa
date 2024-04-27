package com.etiqa.onlinestore.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import com.etiqa.onlinestore.entity.ProductEntity;

public interface ProductService {
	
    ProductEntity createProduct(ProductEntity product);
    Page<ProductEntity> searchProducts(String searchTerm, Pageable pageable);
    Optional<ProductEntity> findById(Long id);
    ProductEntity updateProduct(Long id, ProductEntity productDetails);
    ProductEntity partialUpdateProduct(Long id, ProductEntity productDetails);
    void deleteProduct(Long id);
}
