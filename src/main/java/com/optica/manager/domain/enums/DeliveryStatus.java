package com.optica.manager.domain.enums;

public enum DeliveryStatus {
    PENDING("Pendente"),
    DELIVERED("Entregue");

    private final String label;

    DeliveryStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
