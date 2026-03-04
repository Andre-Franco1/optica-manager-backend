package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.SaleStatus;

public record SaleRequest(
        LocalDate issueDate,
        LocalDate estimatedDeliveryDate,
        LocalDate deliveryDate,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CardBrand cardBrand,
        Integer installments,
        String comments,
        SaleStatus saleStatus,
        LongDTO client,
        Long prescriptionId,
        List<SaleItemRequest> saleItems) {

}
