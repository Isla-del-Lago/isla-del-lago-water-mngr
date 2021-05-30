package com.isladellago.watercalculator.model.consumption;

import javax.persistence.*;
import java.util.Date;

/**
 * Class responsible for map the consumption entity on isla_del_lago database.
 */
@Entity
@Table(name = "consumption")
public final class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumption_id")
    private Integer consumptionId;

    @Column(name = "apartment_name")
    private String apartmentName;

    @Column(name = "bill_date")
    private String billDate;

    @Column(name = "meter_value")
    private String meterValue;

    @Column(name = "value_photo_url")
    private String valuePhotoUrl;

    @Column(name = "creation_date")
    private Date creationDate = new Date();

    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getMeterValue() {
        return meterValue;
    }

    public void setMeterValue(String meterValue) {
        this.meterValue = meterValue;
    }

    public String getValuePhotoUrl() {
        return valuePhotoUrl;
    }

    public void setValuePhotoUrl(String valuePhotoUrl) {
        this.valuePhotoUrl = valuePhotoUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
