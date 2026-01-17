package com.optica.manager.domain.entities;

import com.optica.manager.domain.enums.FrameBrand;
import com.optica.manager.domain.enums.FrameType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "frames")
@PrimaryKeyJoinColumn(name = "product_id")
public class Frame extends Product {

    @Enumerated(EnumType.STRING)
    private FrameBrand brand;

    @Enumerated(EnumType.STRING)
    private FrameType type;

    private Integer stockQuantity;

    public Frame() {
    }

    public Frame(String code, String name, FrameBrand brand, FrameType type,
            Integer stockQuantity) {
        super(code, name);
        this.brand = brand;
        this.type = type;
        this.stockQuantity = stockQuantity;
    }

    public FrameBrand getBrand() {
        return brand;
    }

    public void setBrand(FrameBrand brand) {
        this.brand = brand;
    }

    public FrameType getType() {
        return type;
    }

    public void setType(FrameType type) {
        this.type = type;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Frame [frameType=" + type + ", stockQuantity=" + stockQuantity + " " + super.toString()
                + "]";
    }

}
