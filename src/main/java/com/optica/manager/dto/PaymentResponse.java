package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.PaymentStatus;

public record PaymentResponse(
        Long id,
        BigDecimal amount,
        Integer installments,
        LocalDate paymentDate,
        PaymentMethod paymentMethod,
        CardBrand cardBrand,
        PaymentStatus status,
        List<PaymentInstallmentResponse> paymentInstallments) {

}
