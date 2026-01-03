package com.optica.manager.dto.response;

import java.math.BigDecimal;

import com.optica.manager.dto.LongDTO;

public record SaleItemResponse (
    LongDTO product,
    BigDecimal price){
    
}
