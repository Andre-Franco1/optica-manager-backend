package com.optica.manager.dto;

import java.math.BigDecimal;

import com.optica.manager.domain.enums.LensType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LensRequest (

    @NotBlank(message = "O código é obrigatório.") String code,
    @NotBlank(message = "O nome é obrigatório.") String name,
    @NotNull(message = "O preço de custo é obrigatório.") @DecimalMin(value = "0.0", inclusive = false, message = "O preço de custo deve ser maior que zero.") BigDecimal costPrice,
    @NotNull(message = "O preço de venda é obrigatório.") @DecimalMin(value = "0.0", inclusive = false, message = "O preço de venda deve ser maior que zero.")BigDecimal salePrice,
    @NotNull(message = "O tipo da lente é obrigatório.") LensType lensType) {
    
}
