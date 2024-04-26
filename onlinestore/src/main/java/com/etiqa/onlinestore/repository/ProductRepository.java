package com.etiqa.onlinestore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etiqa.onlinestore.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
    @Query("SELECT p FROM ProductEntity p WHERE (p.bookTitle LIKE %:searchTerm%) AND p.deleted = false")
    Page<ProductEntity> findBySearchTerm(String searchTerm, Pageable pageable);
    
    @Query("SELECT p FROM ProductEntity p WHERE p.deleted = false")
    List<ProductEntity> findAllActive();

    @Transactional
    @Modifying
    @Query("UPDATE ProductEntity p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(Long id);
}
