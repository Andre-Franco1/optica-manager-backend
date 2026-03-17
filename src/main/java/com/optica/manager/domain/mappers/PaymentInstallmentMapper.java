package com.optica.manager.domain.mappers;

import com.optica.manager.domain.entities.PaymentInstallment;
import com.optica.manager.dto.PaymentInstallmentResponse;

public class PaymentInstallmentMapper {

    public static PaymentInstallmentResponse toPaymentInstallmentResponseDTO(PaymentInstallment paymentInstallment) {
                
        return new PaymentInstallmentResponse(
            paymentInstallment.getId(), 
            paymentInstallment.getInstallmentNumber(),
            paymentInstallment.getDueDate(),
            paymentInstallment.getAmount());
    }

}
