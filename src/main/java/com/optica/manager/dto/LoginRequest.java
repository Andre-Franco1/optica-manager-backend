package com.optica.manager.dto;

public record LoginRequest (
    String email,
    String password) {
    
}
