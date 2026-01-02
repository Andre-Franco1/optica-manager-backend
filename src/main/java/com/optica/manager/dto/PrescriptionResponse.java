package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrescriptionResponse (
    Long id,
    LocalDate date,
    BigDecimal od_sphere,
    BigDecimal od_cylinder,
    Integer od_axis,
    BigDecimal os_sphere,
    BigDecimal os_cylinder,
    Integer os_axis,
    BigDecimal addition,
    String notes,
    Long clientId) {

}
