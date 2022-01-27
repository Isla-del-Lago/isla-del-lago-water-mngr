package com.isladellago.watercalculator.domain.model.consumptiondetail;

import javax.persistence.*;

/**
 * This class is responsible for map the get apartment details
 * request response.
 */
@Entity
@Table(name = "consumption_detail")
public class ConsumptionDetail {

    @Id
    @Column(name = "consumption_id")
    private int consumptionId;

    @Column
    private double discount;

    @Column
    private double cleaning;

    @Column
    private double total;

    @OneToOne(mappedBy = "consumptionDetail", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CubicMetersDetail cubicMetersDetail;

    @OneToOne(mappedBy = "consumptionDetail", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AcueductoDetail acueductoDetail;

    @OneToOne(mappedBy = "consumptionDetail", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AlcantarilladoDetail alcantarilladoDetail;

    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
    }

    public CubicMetersDetail getCubicMetersDetail() {
        return cubicMetersDetail;
    }

    public void setCubicMetersDetail(CubicMetersDetail cubicMetersDetail) {
        this.cubicMetersDetail = cubicMetersDetail;
    }

    public AcueductoDetail getAcueductoDetail() {
        return acueductoDetail;
    }

    public void setAcueductoDetail(AcueductoDetail acueductoDetail) {
        this.acueductoDetail = acueductoDetail;
    }

    public AlcantarilladoDetail getAlcantarilladoDetail() {
        return alcantarilladoDetail;
    }

    public void setAlcantarilladoDetail(AlcantarilladoDetail alcantarilladoDetail) {
        this.alcantarilladoDetail = alcantarilladoDetail;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCleaning() {
        return cleaning;
    }

    public void setCleaning(double cleaning) {
        this.cleaning = cleaning;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
