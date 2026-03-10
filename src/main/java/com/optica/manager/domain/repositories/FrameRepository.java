package com.optica.manager.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Frame;

public interface FrameRepository extends JpaRepository<Frame, Long> {
    
    Page<Frame> findByNameContainingIgnoreCaseAndUnitId(String name, Pageable pageable, Integer unitId);

    List<Frame> findAllByUnitId(Integer unitId);

    Optional<Frame> findByIdAndUnitId(Long id, Integer unitId);

    boolean existsByIdAndUnitId(Long id, Integer unitId);

    void deleteByIdAndUnitId(Long id, Integer unitId);
}
