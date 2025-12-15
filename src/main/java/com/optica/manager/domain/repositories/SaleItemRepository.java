package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
    
}
