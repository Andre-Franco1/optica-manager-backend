package com.optica.manager.domain.entities;

import com.optica.manager.domain.enums.LensType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "lenses")
@PrimaryKeyJoinColumn(name = "product_id")
public class Lens extends Product {

    @Enumerated(EnumType.STRING)
    private LensType lensType;

    public LensType getLensType() {
        return lensType;
    }

    public void setLensType(LensType lensType) {
        this.lensType = lensType;
    }

    @Override
    public String toString() {
        return "Lens [lensType=" + lensType + " " + super.toString() + "]";
    }

}
