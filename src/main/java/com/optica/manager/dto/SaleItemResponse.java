package com.optica.manager.dto;

import java.math.BigDecimal;

public record SaleItemResponse (
    Long productId,
    BigDecimal price,
    Integer quantity){
    
}
