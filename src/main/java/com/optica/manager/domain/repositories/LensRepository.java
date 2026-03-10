package com.optica.manager.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Lens;

public interface LensRepository extends JpaRepository<Lens, Long> {
    
    Page<Lens> findByNameContainingIgnoreCaseAndUnitId(String name, Pageable pageable, Integer unitId);

    List<Lens> findAllByUnitId(Integer unitId);

    Optional<Lens> findByIdAndUnitId(Long id, Integer unitId);

    boolean existsByIdAndUnitId(Long id, Integer unitId);

    void deleteByIdAndUnitId(Long id, Integer unitId);
}
