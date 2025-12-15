package com.optica.manager.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optica.manager.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
