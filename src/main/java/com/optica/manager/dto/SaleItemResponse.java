package com.optica.manager.dto;

import java.math.BigDecimal;

public record SaleItemResponse (
    LongDTO product,
    BigDecimal price){
    
}
