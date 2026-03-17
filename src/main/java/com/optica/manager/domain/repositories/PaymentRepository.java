package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
