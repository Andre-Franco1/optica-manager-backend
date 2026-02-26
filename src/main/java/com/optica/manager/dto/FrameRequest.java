package com.optica.manager.dto;

import com.optica.manager.domain.enums.FrameBrand;
import com.optica.manager.domain.enums.FrameType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FrameRequest (

    @NotBlank(message = "O código é obrigatório.") String code,
    @NotBlank(message = "O nome é obrigatório.") String name,
    @NotNull(message = "A marca é obrigatória.") FrameBrand brand,
    @NotNull(message = "O tipo é obrigatório.") FrameType type,
    @Min(value = 0, message = "A quantidade não pode ser negativa.") Integer stockQuantity) {
    
}
