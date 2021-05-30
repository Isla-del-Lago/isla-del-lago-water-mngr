package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;

public interface WaterCalculatorService {

    /**
     * This method saves a new Consumption from the given body info.
     *
     * @param consumptionRequestBodyDto Request body.
     * @return Request response with the consumption id generated.
     */
    CreateConsumptionResponseDto createConsumption(CreateConsumptionRequestBodyDto consumptionRequestBodyDto);

}
