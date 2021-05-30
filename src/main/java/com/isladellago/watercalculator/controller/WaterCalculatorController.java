package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;
import com.isladellago.watercalculator.service.WaterCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/water")
@CrossOrigin("*")
public class WaterCalculatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaterCalculatorController.class);

    private WaterCalculatorService waterCalculatorService;

    @PostMapping("/consumption")
    public final ResponseEntity<CreateConsumptionResponseDto> createConsumption(
            @RequestBody CreateConsumptionRequestBodyDto consumption) {
        LOGGER.info("[CREATE CONSUMPTION] REQUEST RECEIVED");

        final CreateConsumptionResponseDto createConsumptionResponse =
                waterCalculatorService.createConsumption(consumption);

        return new ResponseEntity<>(createConsumptionResponse, HttpStatus.CREATED);
    }

    @Autowired
    public void setWaterCalculatorService(WaterCalculatorService waterCalculatorService) {
        this.waterCalculatorService = waterCalculatorService;
    }

}
