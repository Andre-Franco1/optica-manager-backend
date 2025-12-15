package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Frame;

public interface FrameRepository extends JpaRepository<Frame, Long> {
    
}
