package com.optica.manager.domain.mappers;

import java.math.BigDecimal;
import java.util.List;

import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.enums.SalePaymentStatus;
import com.optica.manager.domain.financial.SaleFinancialService;
import com.optica.manager.dto.PaymentInstallmentPdfDTO;
import com.optica.manager.dto.PaymentPdfDTO;
import com.optica.manager.dto.PrescriptionPdfDTO;
import com.optica.manager.dto.SaleItemPdfDTO;
import com.optica.manager.dto.ServiceOrderPdfDTO;

public class ServiceOrderMapper {

        public static ServiceOrderPdfDTO toServiceOrderDto(Sale sale) {

                List<SaleItemPdfDTO> items = sale.getSaleItems()
                                .stream().map(item -> new SaleItemPdfDTO(
                                                item.getUnitPrice(),
                                                item.getProduct().getId(),
                                                item.getProduct().getName(),
                                                item.getQuantity(),
                                                item.getSubtotal()))
                                .toList();

                List<PaymentPdfDTO> payments = sale.getPayments()
                                .stream().map(payment -> {

                                        List<PaymentInstallmentPdfDTO> installments = payment.getInstallmentsList()
                                                        .stream()
                                                        .map(inst -> new PaymentInstallmentPdfDTO(
                                                                        inst.getInstallmentNumber(),
                                                                        inst.getDueDate(),
                                                                        inst.getAmount()))
                                                        .toList();

                                        return new PaymentPdfDTO(
                                                        payment.getPaymentMethod(),
                                                        payment.getCardBrand(),
                                                        payment.getAmount(),
                                                        installments);
                                }).toList();

                PrescriptionPdfDTO prescriptionPdfDTO = null;

                if (sale.getPrescription() != null) {
                        prescriptionPdfDTO = PrescriptionMapper.toPrescriptionPdfDTO(sale.getPrescription());
                }

                BigDecimal paidAmount = SaleFinancialService.calculateAmountPaid(sale);
                BigDecimal remainingAmount = SaleFinancialService.calculateRemainingPayment(sale);
                SalePaymentStatus salePaymentStatus = SaleFinancialService.calculateSalePaymentStatus(sale);

                return new ServiceOrderPdfDTO(
                                sale.getId(),
                                sale.getClient().getId(),
                                sale.getClient().getName(),
                                sale.getClient().getCpf(),
                                sale.getIssueDate(),
                                sale.getDeliveryStatus(),
                                sale.getEstimatedDeliveryDate(),
                                sale.getTotalAmount(),
                                paidAmount,
                                remainingAmount,
                                salePaymentStatus,
                                sale.getUser().getName(),
                                sale.getUnit().getName(),
                                sale.getUnit().getAddress(),
                                sale.getUnit().getLogoPath(),
                                items,
                                payments,
                                prescriptionPdfDTO);
        }

}
