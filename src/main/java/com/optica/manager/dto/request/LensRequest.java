package com.optica.manager.dto.request;

import java.util.Set;

import com.optica.manager.domain.enums.LensBrand;
import com.optica.manager.domain.enums.LensIndex;
import com.optica.manager.domain.enums.LensMaterial;
import com.optica.manager.domain.enums.LensTreatment;
import com.optica.manager.domain.enums.LensType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LensRequest (

    @NotBlank(message = "O código é obrigatório.") String code,
    @NotBlank(message = "O nome é obrigatório.") String name,
    @NotNull(message = "A marca da lente é obrigatória.") LensBrand brand,
    @NotNull(message = "O índice da lente é obrigatório.") LensIndex index,
    @NotNull(message = "O material da lente é obrigatório.") LensMaterial material,
    @NotNull(message = "O tratamento da lente é obrigatório.") Set<LensTreatment> treatments,
    @NotNull(message = "O tipo da lente é obrigatório.") LensType type) {
    
}
