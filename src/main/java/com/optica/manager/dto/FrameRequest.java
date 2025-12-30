package com.optica.manager.dto;

import java.math.BigDecimal;

import com.optica.manager.domain.enums.FrameCategory;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FrameRequest (

    @NotBlank(message = "O código é obrigatório.") String code,
    @NotBlank(message = "O nome é obrigatório.") String name,
    @NotNull(message = "O preço de custo é obrigatório.") @DecimalMin(value = "0.0", inclusive = false, message = "O preço de custo deve ser maior que zero.") BigDecimal costPrice,
    @NotNull(message = "O preço de venda é obrigatório.") @DecimalMin(value = "0.0", inclusive = false, message = "O preço de venda deve ser maior que zero.")BigDecimal salePrice,
    @NotNull(message = "A categoria é obrigatória.") FrameCategory frameCategory,
    @NotNull(message = "A quantidade em estoque é obrigatória.") @Min(value = 0, message = "A quantidade não pode ser negativa.") Integer stockQuantity) {
    
}
