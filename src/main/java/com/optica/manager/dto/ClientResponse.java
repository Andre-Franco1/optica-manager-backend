package com.optica.manager.dto;

public record ClientResponse(
        Long id,
        String cpf,
        String name,
        String phone) {

}
