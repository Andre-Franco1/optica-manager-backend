package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.dto.FrameRequest;
import com.optica.manager.dto.FrameResponse;

public class FrameMapper {
    
    public static FrameResponse toFrameResponseDTO(Frame frame) {
        return new FrameResponse(frame.getId(), frame.getCode(), frame.getName(), frame.getCostPrice(), frame.getSalePrice(), frame.getFrameCategory(), frame.getStockQuantity());
    }

    public static Frame fromFrameRequestDTO(FrameRequest frameRequest) {
        return new Frame(
                frameRequest.code(),
                frameRequest.name(),
                frameRequest.costPrice(),
                frameRequest.salePrice(),
                frameRequest.frameCategory(),
                frameRequest.stockQuantity());
    }
}
