package com.optica.manager.domain.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Unit;
import com.optica.manager.domain.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TenantService {

    @PersistenceContext
    private EntityManager entityManager;
    
    public Integer getUnitId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUnit().getId();
    }

    public Unit getUnitReference() {
        return entityManager.getReference(Unit.class, getUnitId());
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}