package com.optica.manager.dto;

import com.optica.manager.domain.enums.FrameBrand;
import com.optica.manager.domain.enums.FrameType;

public record FrameResponse (
    Long id,
    String code,
    String name,
    FrameBrand brand,
    FrameType type,
    Integer stockQuantity) {
    
}
