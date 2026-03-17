package com.optica.manager.domain.enums;

public enum SalePaymentStatus {
    PENDING("Pendente"),
    PARTIALLY_PAID("Parcial"),
    PAID("Pago");

    private final String label;

    SalePaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
