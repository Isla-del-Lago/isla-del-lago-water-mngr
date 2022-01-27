package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.domain.dto.consumptiondetail.ConsumptionDetailResponse;

import java.util.List;

public interface ApartmentService {

    /**
     * This method map all necessary values to show usefull
     * information about apartments consumpitons.
     *
     * @param billDate Bill date which consumptions will
     *                 be calculated.
     * @return Consumptions details from the given bill date.
     */
    void saveConsumptionDetails(String billDate);

    /**
     * This method returns a consumption detail from the given
     * apartment name and bill date.
     *
     * @param aptName  Apartment name.
     * @param billDate Bill date.
     * @return Consumption detail if exists.
     */
    ConsumptionDetailResponse getConsumptionDetailFromAptNameAndBillDate(String aptName, String billDate);

    /**
     * This method returns a list with all consumption details.
     *
     * @return List with consumption details.
     */
    List<ConsumptionDetailResponse> getAllConsumptionDetails();

    /**
     * This method returns all consumption details related to
     * the given bill date.
     *
     * @param billDate Bill date to look for consumption details.
     * @return List with consumption Details.
     */
    List<ConsumptionDetailResponse> getAllConsumptionDetailsFromBillDate(String billDate);

    /**
     * This method returns all consumption detailes related to
     * the given apt name.
     *
     * @param aptName Apt name to look for consumption details.
     * @return List with Consumption Details.
     */
    List<ConsumptionDetailResponse> getAllConsumptionDetailsFromAptName(String aptName);
}
