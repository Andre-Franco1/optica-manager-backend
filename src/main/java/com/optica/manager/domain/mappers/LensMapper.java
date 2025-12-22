package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Lens;
import com.optica.manager.dto.LensResponse;

public class LensMapper {
    public static LensResponse toLensResponseDTO(Lens lens) {
        return new LensResponse(lens.getId(), lens.getCode(), lens.getName(), lens.getCostPrice(), lens.getSalePrice(), lens.getLensType());
    }
}
