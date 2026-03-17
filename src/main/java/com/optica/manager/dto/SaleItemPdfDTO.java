package com.optica.manager.dto;

import java.math.BigDecimal;

public record SaleItemPdfDTO (
    BigDecimal unitPrice,
    Long productId,
    String productName,
    Integer quantity,
    BigDecimal subtotal){
    
}
