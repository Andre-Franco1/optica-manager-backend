package com.optica.manager.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findAllByClientId(Long clientId);

    Optional<Prescription> findByIdAndClientId(Long id, Long clientId);

    boolean existsByIdAndClientId(Long id, Long clientId);

    long deleteByIdAndClientId(Long id, Long clientId);

}
