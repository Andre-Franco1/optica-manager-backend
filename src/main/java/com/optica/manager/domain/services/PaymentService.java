package com.optica.manager.domain.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Payment;
import com.optica.manager.domain.entities.PaymentInstallment;
import com.optica.manager.domain.entities.Sale;
import com.optica.manager.domain.mappers.PaymentMapper;
import com.optica.manager.domain.services.exceptions.BusinessException;
import com.optica.manager.domain.services.validators.PaymentValidator;
import com.optica.manager.dto.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private PaymentValidator paymentValidator;

    public void processPayments(Sale sale, List<PaymentRequest> payments) {

        BigDecimal totalPayments = BigDecimal.ZERO;

        for (PaymentRequest paymentRequest : payments) {

            Payment payment = PaymentMapper.fromPaymentRequestDTO(paymentRequest);
            payment.setSale(sale);
            paymentValidator.validatePayment(payment);
            generateInstallments(payment);
            sale.getPayments().add(payment);
            totalPayments = totalPayments.add(payment.getAmount());
        }

        if (totalPayments.compareTo(sale.getTotalAmount()) > 0) {
            throw new BusinessException("Pagamentos excederam o valor total da compra");
        }
    }

    private void generateInstallments(Payment payment) {

        if (payment.getInstallments() == null) {
            return;
        }

        BigDecimal installmentValue = payment.getAmount().divide(
                BigDecimal.valueOf(payment.getInstallments()),
                2,
                RoundingMode.HALF_UP);

        for (int i = 1; i <= payment.getInstallments(); i++) {

            PaymentInstallment installment = new PaymentInstallment();

            installment.setInstallmentNumber(i);
            installment.setAmount(installmentValue);
            installment.setDueDate(payment.getPaymentDate().plusMonths(i - 1));
            installment.setPayment(payment);

            payment.getInstallmentsList().add(installment);
        }
    }

}
