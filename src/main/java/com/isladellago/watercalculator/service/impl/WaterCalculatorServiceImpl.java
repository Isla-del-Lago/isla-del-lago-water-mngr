package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;
import com.isladellago.watercalculator.model.consumption.Consumption;
import com.isladellago.watercalculator.model.consumption.ConsumptionRepository;
import com.isladellago.watercalculator.service.WaterCalculatorService;
import com.isladellago.watercalculator.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterCalculatorServiceImpl implements WaterCalculatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaterCalculatorServiceImpl.class);

    private ConsumptionRepository consumptionRepository;

    @Override
    public CreateConsumptionResponseDto createConsumption(CreateConsumptionRequestBodyDto consumptionRequestBodyDto) {
        LOGGER.info("[CREATE CONSUMPTION] METHOD START, CREATE CONSUMPTION BODY DTO: {}",
                JacksonUtils.getJsonStringFromObject(consumptionRequestBodyDto));

        final Consumption consumption = mapConsumption(consumptionRequestBodyDto);
        final Integer consumptionId = consumptionRepository.save(consumption).getConsumptionId();

        LOGGER.info("[CREATE CONSUMPTION] METHOD END, CONSUMPTION ID: {}", consumptionId);

        return new CreateConsumptionResponseDto(consumptionId);
    }

    private Consumption mapConsumption(CreateConsumptionRequestBodyDto createConsumptionRequestBodyDto) {
        LOGGER.info("[MAP CONSUMPTION] METHOD START");

        final Consumption consumption = new Consumption();
        consumption.setApartmentName(createConsumptionRequestBodyDto.getApartmentName());
        consumption.setBillDate(createConsumptionRequestBodyDto.getBillDate());
        consumption.setMeterValue(createConsumptionRequestBodyDto.getMeterValue());
        consumption.setValuePhotoUrl(createConsumptionRequestBodyDto.getValuePhotoUrl());

        LOGGER.info("[MAP CONSUMPTION] METHOD END, FINAL CONSUMPTION: {}",
                JacksonUtils.getJsonStringFromObject(consumption));

        return consumption;
    }

    @Autowired
    public void setConsumptionRepository(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }
}
