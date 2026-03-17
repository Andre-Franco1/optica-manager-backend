package com.optica.manager.domain.mappers;

import java.util.List;

import com.optica.manager.domain.entities.Payment;
import com.optica.manager.dto.PaymentInstallmentResponse;
import com.optica.manager.dto.PaymentRequest;
import com.optica.manager.dto.PaymentResponse;

public class PaymentMapper {

    public static PaymentResponse toPaymentResponseDTO(Payment payment) {

        List<PaymentInstallmentResponse> paymentInstallments = payment.getInstallmentsList()
                .stream()
                .map(PaymentInstallmentMapper::toPaymentInstallmentResponseDTO)
                .toList();
                
        return new PaymentResponse(
            payment.getId(), 
            payment.getAmount(), 
            payment.getInstallments(), 
            payment.getPaymentDate(), 
            payment.getPaymentMethod(), 
            payment.getCardBrand(), 
            payment.getStatus(), 
            paymentInstallments);
    }

    public static Payment fromPaymentRequestDTO(PaymentRequest paymentRequest) {
        return new Payment(
                paymentRequest.amount(),
                paymentRequest.installments(),
                paymentRequest.paymentDate(),
                paymentRequest.paymentMethod(),
                paymentRequest.cardBrand(),
                paymentRequest.status());
    }

}
