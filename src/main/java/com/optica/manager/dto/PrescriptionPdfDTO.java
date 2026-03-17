package com.optica.manager.dto;

import java.math.BigDecimal;

public record PrescriptionPdfDTO(
                String ophthalmologistName,

                BigDecimal distanceOdSpherical,
                BigDecimal distanceOdCylindrical,
                Integer distanceOdAxis,
                BigDecimal distanceOdDnp,
                BigDecimal distanceOdAddition,

                BigDecimal distanceOsSpherical,
                BigDecimal distanceOsCylindrical,
                Integer distanceOsAxis,
                BigDecimal distanceOsDnp,
                BigDecimal distanceOsAddition,

                BigDecimal distanceDp,

                BigDecimal nearOdSpherical,
                BigDecimal nearOdCylindrical,
                Integer nearOdAxis,
                BigDecimal nearOdDnp,
                BigDecimal nearOdHeight,

                BigDecimal nearOsSpherical,
                BigDecimal nearOsCylindrical,
                Integer nearOsAxis,
                BigDecimal nearOsDnp,
                BigDecimal nearOsHeight,

                BigDecimal nearDp) {

}
