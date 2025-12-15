package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
