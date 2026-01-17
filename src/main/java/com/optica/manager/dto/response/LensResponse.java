package com.optica.manager.dto.response;

import java.util.Set;

import com.optica.manager.domain.enums.LensBrand;
import com.optica.manager.domain.enums.LensIndex;
import com.optica.manager.domain.enums.LensMaterial;
import com.optica.manager.domain.enums.LensTreatment;
import com.optica.manager.domain.enums.LensType;

public record LensResponse (
    Long id,
    String code,
    String name,
    LensBrand brand,
    LensIndex index,
    LensMaterial material,
    Set<LensTreatment> treatments,
    LensType type) {
    
}
