package com.optica.manager.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockRequest(
        @NotNull(message = "A quantidade é obrigatória.")
        @Min(1)  
        Integer quantity) {

}
