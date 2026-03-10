package com.optica.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.optica.manager.domain.enums.SaleStatus;

public record ServiceOrderPdfDTO (
    Long osNumber,
    Long clientId,
    String clientName,
    String clientCpf,
    LocalDate issueDate,
    SaleStatus saleStatus,
    LocalDate estimatedDeliveryDate,
    BigDecimal totalAmount,
    String userName,
    String unitName,
    String unitAddressInfo,
    String unitLogoPath,
    List<SaleItemPdfDTO> saleItems){
    
}
