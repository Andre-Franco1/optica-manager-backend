package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentInstallmentResponse(
        Long id,
        Integer installmentNumber,
        LocalDate dueDate,
        BigDecimal amount) {

}
