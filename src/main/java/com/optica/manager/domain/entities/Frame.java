package com.optica.manager.domain.entities;

import java.math.BigDecimal;

import com.optica.manager.domain.enums.FrameCategory;

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
    private FrameCategory frameCategory;

    private Integer stockQuantity;

    public Frame() {
    }

    public Frame(String code, String name, BigDecimal costPrice, BigDecimal salePrice, FrameCategory frameCategory,
            Integer stockQuantity) {
        super(code, name, costPrice, salePrice);
        this.frameCategory = frameCategory;
        this.stockQuantity = stockQuantity;
    }

    public FrameCategory getFrameCategory() {
        return frameCategory;
    }

    public void setFrameCategory(FrameCategory frameCategory) {
        this.frameCategory = frameCategory;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Frame [frameCategory=" + frameCategory + ", stockQuantity=" + stockQuantity + " " + super.toString()
                + "]";
    }

}
