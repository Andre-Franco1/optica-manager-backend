package com.optica.manager.dto.request;

import java.math.BigDecimal;

import com.optica.manager.dto.LongDTO;

public record SaleItemRequest (
    LongDTO product,
    BigDecimal price){
    
}
