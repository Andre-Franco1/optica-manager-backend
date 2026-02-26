package com.optica.manager.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
        @NotBlank(message = "CPF requerido") String cpf,
        @NotBlank(message = "Nome requerido") String name,        
        @NotBlank(message = "Telefone requerido") String phone) {

}
