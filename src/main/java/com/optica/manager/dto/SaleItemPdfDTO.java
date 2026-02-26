package com.optica.manager.dto;

import java.math.BigDecimal;

public record SaleItemPdfDTO (
    BigDecimal price,
    Long productId,
    String productName){
    
}
