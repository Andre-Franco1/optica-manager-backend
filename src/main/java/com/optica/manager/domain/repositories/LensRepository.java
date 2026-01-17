package com.optica.manager.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Lens;

public interface LensRepository extends JpaRepository<Lens, Long> {
    
    Page<Lens> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
