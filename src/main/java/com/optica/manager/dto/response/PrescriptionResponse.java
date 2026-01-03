package com.optica.manager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrescriptionResponse(
        Long id,
        LocalDate date,
        BigDecimal distanceOdSpherical,
        BigDecimal distanceOdCylindrical,
        Integer distanceOdAxis,
        BigDecimal distanceOdDnp,
        BigDecimal distanceOdAddition,
        BigDecimal distanceOdDp,

        BigDecimal distanceOsSpherical,
        BigDecimal distanceOsCylindrical,
        Integer distanceOsAxis,
        BigDecimal distanceOsDnp,
        BigDecimal distanceOsAddition,
        BigDecimal distanceOsDp,

        BigDecimal nearOdSpherical,
        BigDecimal nearOdCylindrical,
        Integer nearOdAxis,
        BigDecimal nearOdDnp,
        BigDecimal nearOdHeight,
        BigDecimal nearOdDp,

        BigDecimal nearOsSpherical,
        BigDecimal nearOsCylindrical,
        Integer nearOsAxis,
        BigDecimal nearOsDnp,
        BigDecimal nearOsHeight,
        BigDecimal nearOsDp,
        Integer ophthalmologistId,
        String ophthalmologistName,
        Long clientId,
        String notes) {

}
