package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentInstallmentPdfDTO(
        Integer installmentNumber,
        LocalDate dueDate,
        BigDecimal amount) {

}
