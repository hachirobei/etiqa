package com.etiqa.onlinestore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.etiqa.onlinestore.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
    @Query("SELECT c FROM CustomerEntity c WHERE (c.firstName LIKE %:searchTerm% OR c.lastName LIKE %:searchTerm%) AND c.deleted = false")
    Page<CustomerEntity> findBySearchTerm(String searchTerm, Pageable pageable);


    @Query("SELECT c FROM CustomerEntity c WHERE c.deleted = false")
    List<CustomerEntity> findAllActive();

    @Transactional
    @Modifying
    @Query("UPDATE CustomerEntity c SET c.deleted = true WHERE c.id = :id")
    void softDeleteById(Long id);
}

