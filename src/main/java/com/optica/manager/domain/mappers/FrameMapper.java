package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Frame;
import com.optica.manager.dto.request.FrameRequest;
import com.optica.manager.dto.response.FrameResponse;

public class FrameMapper {
    
    public static FrameResponse toFrameResponseDTO(Frame frame) {
        return new FrameResponse(frame.getId(), frame.getCode(), frame.getName(), frame.getBrand(), frame.getType(), frame.getStockQuantity());
    }

    public static Frame fromFrameRequestDTO(FrameRequest frameRequest) {
        return new Frame(
                frameRequest.code(),
                frameRequest.name(),
                frameRequest.brand(),
                frameRequest.type(),
                frameRequest.stockQuantity());
    }
}
