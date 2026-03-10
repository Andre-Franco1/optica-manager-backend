package com.optica.manager.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findAllByClientIdAndUnitId(Long clientId, Integer unitId);

    Optional<Prescription> findByIdAndUnitId(Long id, Integer unitId);

    Optional<Prescription> findByIdAndClientIdAndUnitId(Long id, Long clientId, Integer unitId);

    boolean existsByIdAndClientIdAndUnitId(Long id, Long clientId, Integer unitId);

    void deleteByIdAndClientIdAndUnitId(Long id, Long clientId, Integer unitId);

}
