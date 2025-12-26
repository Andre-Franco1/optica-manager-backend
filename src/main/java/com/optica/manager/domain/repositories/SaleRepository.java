package com.optica.manager.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.enums.SaleStatus;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
    Page<Sale> findAllBySaleStatus(SaleStatus saleStatus,Pageable pageable);
}
