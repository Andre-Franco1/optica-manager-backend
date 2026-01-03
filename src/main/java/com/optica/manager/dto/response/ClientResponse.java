package com.optica.manager.dto.response;

public record ClientResponse(
        Long id,
        String cpf,
        String name,
        String phone) {

}
