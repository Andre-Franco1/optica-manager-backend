package com.optica.manager.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private BigDecimal distanceOdSpherical;
    private BigDecimal distanceOdCylindrical;
    private Integer distanceOdAxis;
    private BigDecimal distanceOdDnp;
    private BigDecimal distanceOdAddition;
    private BigDecimal distanceOdDp;

    private BigDecimal distanceOsSpherical;
    private BigDecimal distanceOsCylindrical;
    private Integer distanceOsAxis;
    private BigDecimal distanceOsDnp;
    private BigDecimal distanceOsAddition;
    private BigDecimal distanceOsDp;

    private BigDecimal nearOdSpherical;
    private BigDecimal nearOdCylindrical;
    private Integer nearOdAxis;
    private BigDecimal nearOdDnp;
    private BigDecimal nearOdHeight;
    private BigDecimal nearOdDp;

    private BigDecimal nearOsSpherical;
    private BigDecimal nearOsCylindrical;
    private Integer nearOsAxis;
    private BigDecimal nearOsDnp;
    private BigDecimal nearOsHeight;
    private BigDecimal nearOsDp;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "ophthalmologist_id", nullable = false)
    private Ophthalmologist ophthalmologist;

    public Prescription() {
    }

    public Prescription(LocalDate date, BigDecimal distanceOdSpherical, BigDecimal distanceOdCylindrical,
            Integer distanceOdAxis, BigDecimal distanceOdDnp, BigDecimal distanceOdAddition, BigDecimal distanceOdDp,
            BigDecimal distanceOsSpherical, BigDecimal distanceOsCylindrical, Integer distanceOsAxis,
            BigDecimal distanceOsDnp, BigDecimal distanceOsAddition, BigDecimal distanceOsDp,
            BigDecimal nearOdSpherical, BigDecimal nearOdCylindrical, Integer nearOdAxis, BigDecimal nearOdDnp,
            BigDecimal nearOdHeight, BigDecimal nearOdDp, BigDecimal nearOsSpherical, BigDecimal nearOsCylindrical,
            Integer nearOsAxis, BigDecimal nearOsDnp, BigDecimal nearOsHeight, BigDecimal nearOsDp, String notes) {
        this.date = date;
        this.distanceOdSpherical = distanceOdSpherical;
        this.distanceOdCylindrical = distanceOdCylindrical;
        this.distanceOdAxis = distanceOdAxis;
        this.distanceOdDnp = distanceOdDnp;
        this.distanceOdAddition = distanceOdAddition;
        this.distanceOdDp = distanceOdDp;
        this.distanceOsSpherical = distanceOsSpherical;
        this.distanceOsCylindrical = distanceOsCylindrical;
        this.distanceOsAxis = distanceOsAxis;
        this.distanceOsDnp = distanceOsDnp;
        this.distanceOsAddition = distanceOsAddition;
        this.distanceOsDp = distanceOsDp;
        this.nearOdSpherical = nearOdSpherical;
        this.nearOdCylindrical = nearOdCylindrical;
        this.nearOdAxis = nearOdAxis;
        this.nearOdDnp = nearOdDnp;
        this.nearOdHeight = nearOdHeight;
        this.nearOdDp = nearOdDp;
        this.nearOsSpherical = nearOsSpherical;
        this.nearOsCylindrical = nearOsCylindrical;
        this.nearOsAxis = nearOsAxis;
        this.nearOsDnp = nearOsDnp;
        this.nearOsHeight = nearOsHeight;
        this.nearOsDp = nearOsDp;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getDistanceOdSpherical() {
        return distanceOdSpherical;
    }

    public void setDistanceOdSpherical(BigDecimal distanceOdSpherical) {
        this.distanceOdSpherical = distanceOdSpherical;
    }

    public BigDecimal getDistanceOdCylindrical() {
        return distanceOdCylindrical;
    }

    public void setDistanceOdCylindrical(BigDecimal distanceOdCylindrical) {
        this.distanceOdCylindrical = distanceOdCylindrical;
    }

    public Integer getDistanceOdAxis() {
        return distanceOdAxis;
    }

    public void setDistanceOdAxis(Integer distanceOdAxis) {
        this.distanceOdAxis = distanceOdAxis;
    }

    public BigDecimal getDistanceOdDnp() {
        return distanceOdDnp;
    }

    public void setDistanceOdDnp(BigDecimal distanceOdDnp) {
        this.distanceOdDnp = distanceOdDnp;
    }

    public BigDecimal getDistanceOdAddition() {
        return distanceOdAddition;
    }

    public void setDistanceOdAddition(BigDecimal distanceOdAddition) {
        this.distanceOdAddition = distanceOdAddition;
    }

    public BigDecimal getDistanceOdDp() {
        return distanceOdDp;
    }

    public void setDistanceOdDp(BigDecimal distanceOdDp) {
        this.distanceOdDp = distanceOdDp;
    }

    public BigDecimal getDistanceOsSpherical() {
        return distanceOsSpherical;
    }

    public void setDistanceOsSpherical(BigDecimal distanceOsSpherical) {
        this.distanceOsSpherical = distanceOsSpherical;
    }

    public BigDecimal getDistanceOsCylindrical() {
        return distanceOsCylindrical;
    }

    public void setDistanceOsCylindrical(BigDecimal distanceOsCylindrical) {
        this.distanceOsCylindrical = distanceOsCylindrical;
    }

    public Integer getDistanceOsAxis() {
        return distanceOsAxis;
    }

    public void setDistanceOsAxis(Integer distanceOsAxis) {
        this.distanceOsAxis = distanceOsAxis;
    }

    public BigDecimal getDistanceOsDnp() {
        return distanceOsDnp;
    }

    public void setDistanceOsDnp(BigDecimal distanceOsDnp) {
        this.distanceOsDnp = distanceOsDnp;
    }

    public BigDecimal getDistanceOsAddition() {
        return distanceOsAddition;
    }

    public void setDistanceOsAddition(BigDecimal distanceOsAddition) {
        this.distanceOsAddition = distanceOsAddition;
    }

    public BigDecimal getDistanceOsDp() {
        return distanceOsDp;
    }

    public void setDistanceOsDp(BigDecimal distanceOsDp) {
        this.distanceOsDp = distanceOsDp;
    }

    public BigDecimal getNearOdSpherical() {
        return nearOdSpherical;
    }

    public void setNearOdSpherical(BigDecimal nearOdSpherical) {
        this.nearOdSpherical = nearOdSpherical;
    }

    public BigDecimal getNearOdCylindrical() {
        return nearOdCylindrical;
    }

    public void setNearOdCylindrical(BigDecimal nearOdCylindrical) {
        this.nearOdCylindrical = nearOdCylindrical;
    }

    public Integer getNearOdAxis() {
        return nearOdAxis;
    }

    public void setNearOdAxis(Integer nearOdAxis) {
        this.nearOdAxis = nearOdAxis;
    }

    public BigDecimal getNearOdDnp() {
        return nearOdDnp;
    }

    public void setNearOdDnp(BigDecimal nearOdDnp) {
        this.nearOdDnp = nearOdDnp;
    }

    public BigDecimal getNearOdHeight() {
        return nearOdHeight;
    }

    public void setNearOdHeight(BigDecimal nearOdHeight) {
        this.nearOdHeight = nearOdHeight;
    }

    public BigDecimal getNearOdDp() {
        return nearOdDp;
    }

    public void setNearOdDp(BigDecimal nearOdDp) {
        this.nearOdDp = nearOdDp;
    }

    public BigDecimal getNearOsSpherical() {
        return nearOsSpherical;
    }

    public void setNearOsSpherical(BigDecimal nearOsSpherical) {
        this.nearOsSpherical = nearOsSpherical;
    }

    public BigDecimal getNearOsCylindrical() {
        return nearOsCylindrical;
    }

    public void setNearOsCylindrical(BigDecimal nearOsCylindrical) {
        this.nearOsCylindrical = nearOsCylindrical;
    }

    public Integer getNearOsAxis() {
        return nearOsAxis;
    }

    public void setNearOsAxis(Integer nearOsAxis) {
        this.nearOsAxis = nearOsAxis;
    }

    public BigDecimal getNearOsDnp() {
        return nearOsDnp;
    }

    public void setNearOsDnp(BigDecimal nearOsDnp) {
        this.nearOsDnp = nearOsDnp;
    }

    public BigDecimal getNearOsHeight() {
        return nearOsHeight;
    }

    public void setNearOsHeight(BigDecimal nearOsHeight) {
        this.nearOsHeight = nearOsHeight;
    }

    public BigDecimal getNearOsDp() {
        return nearOsDp;
    }

    public void setNearOsDp(BigDecimal nearOsDp) {
        this.nearOsDp = nearOsDp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Ophthalmologist getOphthalmologist() {
        return ophthalmologist;
    }

    public void setOphthalmologist(Ophthalmologist ophthalmologist) {
        this.ophthalmologist = ophthalmologist;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prescription other = (Prescription) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
