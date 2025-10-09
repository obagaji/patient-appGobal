package com.appGobal.patient_appGobal.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;


@Entity
@Table(name="nextOfKin")
@Builder
public class NextOfKin {
    public NextOfKin(Integer nextOfKinId, String firstNameNof,
                     String midlleNameNof, String lastNameNof)
    {
        this.nextOfKinId = nextOfKinId;
        this.firstNameNof=firstNameNof;
        this.midlleNameNof =midlleNameNof;
        this.lastNameNof = lastNameNof;
    }
    public NextOfKin()
    {}
    @Id
    private Integer nextOfKinId;
    private String firstNameNof;
    private String midlleNameNof;
    private String lastNameNof;

    public Integer getNextOfKinId() {
        return nextOfKinId;
    }

    public String getFirstNameNof() {
        return firstNameNof;
    }

    public String getMidlleNameNof() {
        return midlleNameNof;
    }

    public String getLastNameNof() {
        return lastNameNof;
    }

    public void setNextOfKinId(Integer nextOfKinId) {
        this.nextOfKinId = nextOfKinId;
    }

    public void setFirstNameNof(String firstNameNof) {
        this.firstNameNof = firstNameNof;
    }

    public void setMidlleNameNof(String midlleNameNof) {
        this.midlleNameNof = midlleNameNof;
    }

    public void setLastNameNof(String lastNameNof) {
        this.lastNameNof = lastNameNof;
    }
}
