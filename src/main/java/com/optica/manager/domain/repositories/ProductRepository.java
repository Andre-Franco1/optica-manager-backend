package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
