package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.optica.manager.domain.enums.DeliveryStatus;

import jakarta.validation.constraints.NotNull;

public record SaleRequest(
        LocalDateTime issueDate,
        LocalDate estimatedDeliveryDate,
        LocalDate deliveryDate,
        @NotNull BigDecimal discountPercentage,
        String comments,
        DeliveryStatus deliveryStatus,
        Long clientId,
        Long prescriptionId,
        List<SaleItemRequest> saleItems,
        List<PaymentRequest> payments) {

}
