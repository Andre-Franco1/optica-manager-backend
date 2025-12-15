package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
}
