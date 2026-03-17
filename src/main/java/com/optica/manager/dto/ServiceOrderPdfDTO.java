package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.optica.manager.domain.enums.DeliveryStatus;
import com.optica.manager.domain.enums.SalePaymentStatus;

public record ServiceOrderPdfDTO(
        Long osNumber,
        Long clientId,
        String clientName,
        String clientCpf,
        LocalDateTime issueDate,
        DeliveryStatus deliveryStatus,
        LocalDate estimatedDeliveryDate,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount,
        SalePaymentStatus salePaymentStatus,
        String userName,
        String unitName,
        String unitAddressInfo,
        String unitLogoPath,
        List<SaleItemPdfDTO> saleItems,
        List<PaymentPdfDTO> payments,
        PrescriptionPdfDTO prescriptionPdfDTO) {

}
