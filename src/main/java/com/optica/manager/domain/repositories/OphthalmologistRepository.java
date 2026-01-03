package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Ophthalmologist;

public interface OphthalmologistRepository extends JpaRepository<Ophthalmologist, Integer> {
    
}
