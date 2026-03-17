package com.optica.manager.domain.services.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.optica.manager.domain.entities.Payment;
import com.optica.manager.domain.enums.PaymentMethod;
import com.optica.manager.domain.services.exceptions.BusinessException;

@Service
public class PaymentValidator {;

    public void validatePayment(Payment payment) {

        validateAmount(payment);
        validatePaymentMethod(payment);
        validateInstallments(payment);
        validateCardBrand(payment);
        validateInstallmentsNumber(payment);
        validateMaximumInstallmentsNumber(payment);
        validatePaymentDate(payment);
    }

    private void validateAmount(Payment payment) {
        if (payment.getAmount() == null || payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Valor do pagamento deve ser maior que zero");
        }
    }

    private void validatePaymentMethod(Payment payment) {
        if (payment.getPaymentMethod() == null) {
            throw new BusinessException("Método de pagamento é obrigatório");
        }
    }

    private void validateInstallments(Payment payment) {
        if (payment.getInstallments() != null &&
                payment.getPaymentMethod() != PaymentMethod.CREDIT_CARD) {
            throw new BusinessException("Parcelamento permitido apenas para cartão de crédito");
        }
    }

    private void validateCardBrand(Payment payment) {
        if (payment.getPaymentMethod() == PaymentMethod.CREDIT_CARD &&
                payment.getCardBrand() == null) {
            throw new BusinessException("Bandeira do cartão é obrigatória");
        }
    }

    private void validateInstallmentsNumber(Payment payment) {
        if (payment.getInstallments() != null && payment.getInstallments() < 1) {
            throw new BusinessException("Número de parcelas inválido");
        }
    }

    private void validateMaximumInstallmentsNumber(Payment payment) {
        if (payment.getInstallments() != null && payment.getInstallments() > 10) {
            throw new BusinessException("Parcelamento máximo permitido é 10x");
        }
    }

    private void validatePaymentDate(Payment payment) {
        if (payment.getPaymentDate() == null) {
            throw new BusinessException("Data do pagamento é obrigatória");
        }
    }

    /*
    if (totalPayments.compareTo(sale.getTotalAmount()) < 0) {
        throw new BusinessException("Valor pago é menor que o total da venda");
    }
    */

}
