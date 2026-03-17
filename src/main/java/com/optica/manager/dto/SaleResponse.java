package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.optica.manager.domain.enums.DeliveryStatus;
import com.optica.manager.domain.enums.SalePaymentStatus;

public record SaleResponse(
        long id,
        LocalDateTime issueDate,
        LocalDate estimatedDeliveryDate,
        LocalDate deliveryDate,
        BigDecimal subtotal,
        BigDecimal discountPercentage,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount,
        SalePaymentStatus salePaymentStatus,
        String comments,
        DeliveryStatus deliveryStatus,
        Long clientId,
        String clientCpf,
        String clientName,
        String userName,
        List<SaleItemResponse> saleItems,
        List<PaymentResponse> payments) {

}
