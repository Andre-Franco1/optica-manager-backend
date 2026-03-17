package com.optica.manager.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleItemRequest (
    @NotNull
    Long productId,

    @NotNull
    @Positive
    BigDecimal price,
    
    @NotNull
    @Positive
    Integer quantity){
    
}
