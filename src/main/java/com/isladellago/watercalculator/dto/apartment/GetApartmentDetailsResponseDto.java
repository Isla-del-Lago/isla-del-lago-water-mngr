package com.isladellago.watercalculator.dto.apartment;

import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.model.consumptiondetail.ConsumptionDetail;

import java.util.Map;

/**
 * This class is responsible for map the get apartments details
 * request.
 */
public final class GetApartmentDetailsResponseDto {

    private Map<String, ConsumptionDetail> apartmentDetail;
    private Bill bill;

    public Map<String, ConsumptionDetail> getApartmentDetail() {
        return apartmentDetail;
    }

    public void setApartmentDetail(Map<String, ConsumptionDetail> apartmentDetail) {
        this.apartmentDetail = apartmentDetail;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
