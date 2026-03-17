package com.optica.manager.dto;

import java.math.BigDecimal;
import java.util.List;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;

public record PaymentPdfDTO(
        PaymentMethod paymentMethod,
        CardBrand cardBrand,
        BigDecimal amount,
        List<PaymentInstallmentPdfDTO> installments) {

}
