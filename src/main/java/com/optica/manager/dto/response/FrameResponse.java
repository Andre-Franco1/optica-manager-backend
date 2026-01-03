package com.optica.manager.dto.response;

import java.math.BigDecimal;

import com.optica.manager.domain.enums.FrameCategory;

public record FrameResponse (
    Long id,
    String code,
    String name,
    BigDecimal costPrice,
    BigDecimal salePrice,
    FrameCategory frameCategory,
    Integer stockQuantity) {
    
}
