package com.optica.manager.domain.mappers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.optica.manager.domain.entities.Client;
import com.optica.manager.domain.entities.Prescription;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.enums.SalePaymentStatus;
import com.optica.manager.domain.financial.SaleFinancialService;
import com.optica.manager.dto.PaymentResponse;
import com.optica.manager.dto.SaleItemResponse;
import com.optica.manager.dto.SaleRequest;
import com.optica.manager.dto.SaleResponse;

public class SaleMapper {

    public static SaleResponse toSaleResponseDTO(Sale sale) {
        
        // for (SaleItem saleItem : sale.getSaleItems()){
        //     saleItems.add(SaleItemMapper.toSaleItemResponseDTO(saleItem));
        // }

        List<SaleItemResponse> saleItems = sale.getSaleItems()
                .stream()
                .map(SaleItemMapper::toSaleItemResponseDTO)
                .toList();

        List<PaymentResponse> payments = sale.getPayments()
                .stream()
                .map(PaymentMapper::toPaymentResponseDTO)
                .toList();

        BigDecimal paidAmount = SaleFinancialService.calculateAmountPaid(sale);
        BigDecimal remainingAmount = SaleFinancialService.calculateRemainingPayment(sale);
        SalePaymentStatus salePaymentStatus = SaleFinancialService.calculateSalePaymentStatus(sale);
        
        return new SaleResponse(
            sale.getId(),
            sale.getIssueDate(),
            sale.getEstimatedDeliveryDate(),
            sale.getDeliveryDate(),
            sale.getSubtotal(),
            sale.getDiscountPercentage(),
            sale.getTotalAmount(),
            paidAmount,
            remainingAmount,
            salePaymentStatus,
            sale.getComments(),
            sale.getDeliveryStatus(),
            sale.getClient().getId(),
            sale.getClient().getCpf(),
            sale.getClient().getName(),
            sale.getUser().getName(),
            saleItems,
            payments
        );
    }

    
    public static Sale fromSaleRequestDTO(SaleRequest saleRequest) {
        Sale sale = new Sale();

        BeanUtils.copyProperties(saleRequest, sale);
        if (saleRequest.prescriptionId() != null) {
            sale.setPrescription(new Prescription(saleRequest.prescriptionId()));
        } else {
            sale.setPrescription(null);
        }
        
        sale.setClient(new Client(saleRequest.clientId()));
        
        return sale;
    }

}