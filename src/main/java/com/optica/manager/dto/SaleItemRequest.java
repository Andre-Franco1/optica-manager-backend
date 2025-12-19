package com.optica.manager.dto;

import java.math.BigDecimal;

public record SaleItemRequest (
    LongDTO product,
    BigDecimal price){
    
}
