package com.optica.manager.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Page<Client> findByNameContainingIgnoreCaseAndUnitId(String name, Pageable pageable, Integer unitId);

    boolean existsByIdAndUnitId(Long id, Integer unitId);

    void deleteByIdAndUnitId(Long id, Integer unitId);

    Optional<Client> findByIdAndUnitId(Long id, Integer unitId);
}
