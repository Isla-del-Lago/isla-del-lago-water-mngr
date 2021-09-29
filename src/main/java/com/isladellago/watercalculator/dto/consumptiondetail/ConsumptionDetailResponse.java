package com.isladellago.watercalculator.dto.consumptiondetail;

import com.isladellago.watercalculator.model.consumptiondetail.ConsumptionDetail;

/**
 * This class is responsible for map the consumption detail response.
 */
public final class ConsumptionDetailResponse {

    private String apartmentName;
    private String billDate;
    private ConsumptionDetail consumptionDetail;

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

    public ConsumptionDetail getConsumptionDetail() {
        return consumptionDetail;
    }

    public void setConsumptionDetail(ConsumptionDetail consumptionDetail) {
        this.consumptionDetail = consumptionDetail;
    }
}
