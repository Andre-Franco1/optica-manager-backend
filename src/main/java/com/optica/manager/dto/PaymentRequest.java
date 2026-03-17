package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(

        @NotNull
        @Positive
        BigDecimal amount,
        
        Integer installments,

        @NotNull
        LocalDate paymentDate,

        @NotNull
        PaymentMethod paymentMethod,
        
        CardBrand cardBrand,
        PaymentStatus status) {

}
