package com.optica.manager.domain.entities;

import java.util.HashSet;
import java.util.Set;

import com.optica.manager.domain.enums.LensBrand;
import com.optica.manager.domain.enums.LensIndex;
import com.optica.manager.domain.enums.LensMaterial;
import com.optica.manager.domain.enums.LensTreatment;
import com.optica.manager.domain.enums.LensType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "lenses")
@PrimaryKeyJoinColumn(name = "product_id")
public class Lens extends Product {

    @Enumerated(EnumType.STRING)
    private LensBrand brand;

    @Enumerated(EnumType.STRING)
    private LensIndex index;

    @Enumerated(EnumType.STRING)
    private LensMaterial material;

    @ElementCollection(targetClass = LensTreatment.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "lens_treatments",joinColumns = @JoinColumn(name = "lens_id"))
    @Enumerated(EnumType.STRING)
    private Set<LensTreatment> treatments = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private LensType type;

    public Lens() {
    }

    public Lens(String code, String name, LensBrand brand, LensIndex index, LensMaterial material,
            Set<LensTreatment> treatments, LensType type) {
        super(code, name);
        this.brand = brand;
        this.index = index;
        this.material = material;
        this.treatments = treatments;
        this.type = type;
    }

    public LensBrand getBrand() {
        return brand;
    }

    public void setBrand(LensBrand brand) {
        this.brand = brand;
    }

    public LensIndex getIndex() {
        return index;
    }

    public void setIndex(LensIndex index) {
        this.index = index;
    }

    public LensMaterial getMaterial() {
        return material;
    }

    public void setMaterial(LensMaterial material) {
        this.material = material;
    }

    public Set<LensTreatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<LensTreatment> treatments) {
        this.treatments = treatments;
    }

    public LensType getType() {
        return type;
    }

    public void setType(LensType type) {
        this.type = type;
    }

    public void addTreatment(LensTreatment treatment) {
        this.treatments.add(treatment);
    }

    public void removeTreatment(LensTreatment treatment) {
        this.treatments.remove(treatment);
    }

    @Override
    public String toString() {
        return "Lens [brand=" + brand + ", index=" + index + ", material=" + material
                + ", type=" + type + "]";
    }

}
