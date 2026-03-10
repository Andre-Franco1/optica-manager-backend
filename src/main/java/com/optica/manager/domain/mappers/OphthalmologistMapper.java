package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.Ophthalmologist;
import com.optica.manager.dto.OphthalmologistResponse;

public class OphthalmologistMapper {

    public static OphthalmologistResponse toOphthalmologistResponseDTO(Ophthalmologist ophthalmologist) {

        OphthalmologistResponse ophthalmologistResponse = new OphthalmologistResponse(
                ophthalmologist.getId(),
                ophthalmologist.getName());
        return ophthalmologistResponse;
    }
}
