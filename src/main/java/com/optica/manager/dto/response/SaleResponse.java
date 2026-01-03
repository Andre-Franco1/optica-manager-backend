package com.optica.manager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.SaleStatus;
import com.optica.manager.dto.IntegerDTO;

public record SaleResponse(
        long id,
        LocalDate issueDate,
        LocalDate estimatedDeliveryDate,
        LocalDate deliveryDate,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CardBrand cardBrand,
        Integer installments,
        String comments,
        SaleStatus saleStatus,
        Long clientId,
        String clientCpf,
        String clientName,
        IntegerDTO user,
        List<SaleItemResponse> saleItems) {

}
