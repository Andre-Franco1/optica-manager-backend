package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.dto.FrameResponse;

public class FrameMapper {
    
    public static FrameResponse toFrameResponseDTO(Frame frame) {
        return new FrameResponse(frame.getId(), frame.getCode(), frame.getName(), frame.getCostPrice(), frame.getSalePrice(), frame.getFrameCategory(), frame.getStockQuantity());
    }
}
