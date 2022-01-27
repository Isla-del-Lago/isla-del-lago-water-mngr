package com.isladellago.watercalculator.domain.dto.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class responsible for map the create consumption request body.
 */
public final class CreateConsumptionRequestBodyDto {

    @JsonProperty("apartmentName")
    private String apartmentName;

    @JsonProperty("billDate")
    private String billDate;

    @JsonProperty("meterValue")
    private String meterValue;

    @JsonProperty("valuePhotoUrl")
    private String valuePhotoUrl;

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
}
