package com.isladellago.watercalculator.model.consumptiondetail;

import javax.persistence.*;

/**
 * This class is responsible for map the acueducto values.
 */
@Entity
@Table(name = "acueducto_detail")
public final class AcueductoDetail {

    @Id
    @Column(name = "consumption_id")
    private int consumptionId;

    @Column(name = "residential_value")
    private double residentialValue;

    @Column(name = "residential_basic_value")
    private double residentialBasicValue;

    @Column(name = "residential_basic_superior_value")
    private double residentialBasicSuperiorValue;

    @OneToOne
    @MapsId
    @JoinColumn(name = "consumption_id")
    private ConsumptionDetail consumptionDetail;

    public ConsumptionDetail getConsumptionDetail() {
        return consumptionDetail;
    }

    public void setConsumptionDetail(ConsumptionDetail consumptionDetail) {
        this.consumptionDetail = consumptionDetail;
    }

    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
    }

    public double getResidentialValue() {
        return residentialValue;
    }

    public void setResidentialValue(double residentialValue) {
        this.residentialValue = residentialValue;
    }

    public double getResidentialBasicValue() {
        return residentialBasicValue;
    }

    public void setResidentialBasicValue(double residentialBasicValue) {
        this.residentialBasicValue = residentialBasicValue;
    }

    public double getResidentialBasicSuperiorValue() {
        return residentialBasicSuperiorValue;
    }

    public void setResidentialBasicSuperiorValue(double residentialBasicSuperiorValue) {
        this.residentialBasicSuperiorValue = residentialBasicSuperiorValue;
    }
}
