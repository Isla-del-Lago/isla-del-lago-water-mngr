package com.isladellago.watercalculator.model.consumptiondetail;

import javax.persistence.*;

/**
 * This class is responsible to hold all cubic meters
 * related values from an apartment.
 */
@Entity
@Table(name = "cubic_meters_detail")
public final class CubicMetersDetail {

    @Id
    @Column(name = "consumption_id")
    private int consumptionId;

    @Column(name = "m3_residential_basic")
    private double m3ResidentialBasic;

    @Column(name = "m3_residential_basic_superior")
    private double m3ResidentialBasicSuperior;

    @Column(name = "total_m3_consumed")
    private double totalM3Consumed;

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

    public double getM3ResidentialBasic() {
        return m3ResidentialBasic;
    }

    public void setM3ResidentialBasic(double m3ResidentialBasic) {
        this.m3ResidentialBasic = m3ResidentialBasic;
    }

    public double getM3ResidentialBasicSuperior() {
        return m3ResidentialBasicSuperior;
    }

    public void setM3ResidentialBasicSuperior(double m3ResidentialBasicSuperior) {
        this.m3ResidentialBasicSuperior = m3ResidentialBasicSuperior;
    }

    public double getTotalM3Consumed() {
        return totalM3Consumed;
    }

    public void setTotalM3Consumed(double totalM3Consumed) {
        this.totalM3Consumed = totalM3Consumed;
    }
}
