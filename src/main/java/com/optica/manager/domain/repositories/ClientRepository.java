package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
