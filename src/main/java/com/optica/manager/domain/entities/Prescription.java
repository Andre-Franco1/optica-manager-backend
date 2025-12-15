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

    private BigDecimal od_sphere;
    private BigDecimal od_cylinder;
    private Integer od_axis;

    private BigDecimal os_sphere;
    private BigDecimal os_cylinder;
    private Integer os_axis;

    private BigDecimal addition;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

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

    public BigDecimal getOd_sphere() {
        return od_sphere;
    }

    public void setOd_sphere(BigDecimal od_sphere) {
        this.od_sphere = od_sphere;
    }

    public BigDecimal getOd_cylinder() {
        return od_cylinder;
    }

    public void setOd_cylinder(BigDecimal od_cylinder) {
        this.od_cylinder = od_cylinder;
    }

    public Integer getOd_axis() {
        return od_axis;
    }

    public void setOd_axis(Integer od_axis) {
        this.od_axis = od_axis;
    }

    public BigDecimal getOs_sphere() {
        return os_sphere;
    }

    public void setOs_sphere(BigDecimal os_sphere) {
        this.os_sphere = os_sphere;
    }

    public BigDecimal getOs_cylinder() {
        return os_cylinder;
    }

    public void setOs_cylinder(BigDecimal os_cylinder) {
        this.os_cylinder = os_cylinder;
    }

    public Integer getOs_axis() {
        return os_axis;
    }

    public void setOs_axis(Integer os_axis) {
        this.os_axis = os_axis;
    }

    public BigDecimal getAddition() {
        return addition;
    }

    public void setAddition(BigDecimal addition) {
        this.addition = addition;
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

    @Override
    public String toString() {
        return "Prescription [id=" + id + ", date=" + date + ", od_sphere=" + od_sphere + ", od_cylinder=" + od_cylinder
                + ", od_axis=" + od_axis + ", os_sphere=" + os_sphere + ", os_cylinder=" + os_cylinder + ", os_axis="
                + os_axis + ", addition=" + addition + ", notes=" + notes + "]";
    }

}
