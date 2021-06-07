package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;
import com.isladellago.watercalculator.model.consumption.Consumption;

import java.util.List;

public interface ConsumptionService {

    /**
     * This method saves a new Consumption from the given body info.
     *
     * @param consumptionRequestBodyDto Request body.
     * @return Request response with the consumption id generated.
     */
    CreateConsumptionResponseDto createConsumption(CreateConsumptionRequestBodyDto consumptionRequestBodyDto);

    /**
     * This method returns all consumptions saved on isla_del_lago
     * database.
     *
     * @return List of consumptions.
     */
    List<Consumption> getAllConsumptions();

    /**
     * This method returns a consumption object from the given id.
     *
     * @param id Consumption id.
     * @return Consumption that belongs to the given id.
     */
    Consumption getConsumptionById(Integer id);

    /**
     * This method returns a list with the consumptions related to the
     * given bill date.
     *
     * @param billDate Bill date to be searched.
     * @return Consumptions list.
     */
    List<Consumption> getConsumptionsByBillDate(String billDate);

    /**
     * This method returns a list with the consumptions related to the
     * given apartment name.
     *
     * @param apartmentName Apartment name with consumptions will be searched.
     * @return Consumptions list.
     */
    List<Consumption> getConsumptionsByApartmentName(String apartmentName);

    /**
     * This method removes a consumption from the given id.
     *
     * @param consumptionId Consumption id.
     */
    void deleteConsumptionById(Integer consumptionId);

    /**
     * This method gets a current consumption from the given
     * consumption.
     *
     * @param currentConsumption
     * @return
     */
    Consumption getPreviousConsumption(Consumption currentConsumption);
}
