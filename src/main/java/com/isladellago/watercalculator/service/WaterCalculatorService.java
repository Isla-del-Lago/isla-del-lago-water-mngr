package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;

public interface WaterCalculatorService {

    CreateConsumptionResponseDto createConsumption(CreateConsumptionRequestBodyDto consumption);

}
