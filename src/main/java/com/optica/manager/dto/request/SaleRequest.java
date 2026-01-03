package com.optica.manager.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.optica.manager.domain.enums.CardBrand;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.enums.SaleStatus;
import com.optica.manager.dto.IntegerDTO;
import com.optica.manager.dto.LongDTO;

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
        IntegerDTO user,
        List<SaleItemRequest> saleItems) {

}
