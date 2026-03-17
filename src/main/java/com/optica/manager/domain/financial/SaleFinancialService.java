package com.optica.manager.domain.financial;

import java.math.BigDecimal;

import com.optica.manager.domain.entities.Payment;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.enums.SalePaymentStatus;

public class SaleFinancialService {
    
    public static BigDecimal calculateAmountPaid(Sale sale) {

        if (sale.getPayments() == null || sale.getPayments().isEmpty()){
            return BigDecimal.ZERO;
        }

        return sale.getPayments().stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal calculateRemainingPayment(Sale sale) {
        BigDecimal total = sale.getTotalAmount() != null ? sale.getTotalAmount() : BigDecimal.ZERO;
        BigDecimal paid = calculateAmountPaid(sale);

        return total.subtract(paid);
    }

    public static SalePaymentStatus calculateSalePaymentStatus(Sale sale) {
        BigDecimal total = sale.getTotalAmount() != null ? sale.getTotalAmount() : BigDecimal.ZERO;
        BigDecimal paid = calculateAmountPaid(sale);

        if (paid.compareTo(BigDecimal.ZERO) == 0) {
            return SalePaymentStatus.PENDING;
        }

        if (paid.compareTo(total) < 0) {
            return SalePaymentStatus.PARTIALLY_PAID;
        }

        return SalePaymentStatus.PAID;
    }
}
