package com.optica.manager.dto.response;

import java.math.BigDecimal;

import com.optica.manager.domain.enums.LensType;

public record LensResponse (
    Long id,
    String code,
    String name,
    BigDecimal costPrice,
    BigDecimal salePrice,
    LensType lensType) {
    
}
