package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;
import com.isladellago.watercalculator.model.consumption.Consumption;
import com.isladellago.watercalculator.service.ConsumptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/water")
@CrossOrigin("*")
public class ConsumptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionController.class);

    private ConsumptionService consumptionService;

    @PostMapping("/consumption")
    public final ResponseEntity<CreateConsumptionResponseDto> createConsumption(
            @RequestBody CreateConsumptionRequestBodyDto consumption) {
        LOGGER.info("[CREATE CONSUMPTION CONTROLLER] REQUEST RECEIVED");

        final CreateConsumptionResponseDto createConsumptionResponse =
                consumptionService.createConsumption(consumption);

        return new ResponseEntity<>(createConsumptionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/consumption")
    public final ResponseEntity<List<Consumption>> getAllConsumptions() {
        LOGGER.info("[GET ALL CONSUMPTIONS CONTROLLER] REQUEST RECEIVED");

        final List<Consumption> consumptions = consumptionService.getAllConsumptions();

        return new ResponseEntity<>(consumptions, HttpStatus.OK);
    }

    @GetMapping("/consumption/{id}")
    public final ResponseEntity<Consumption> getConsumptionById(@PathVariable("id") Integer id) {
        LOGGER.info("[GET CONSUMPTION BY ID CONTROLLER;] REQUEST RECEIVED");

        final var consumption = consumptionService.getConsumptionById(id);

        return new ResponseEntity<>(consumption, HttpStatus.OK);
    }

    @GetMapping("/consumption/bill-date")
    public final ResponseEntity<List<Consumption>> getConsumptionsByBillDate(
            @RequestParam("billDate") String billDate) {
        LOGGER.info("[GET CONSUMPTIONS BY BILL DATE CONTROLLER] REQUEST RECEIVED");

        final List<Consumption> consumptions = consumptionService.getConsumptionsByBillDate(billDate);

        return new ResponseEntity<>(consumptions, HttpStatus.OK);
    }

    @GetMapping("/consumption/apt-name")
    public final ResponseEntity<List<Consumption>> getConsumptionsByApartmentName(
            @RequestParam("aptName") String apartmentName) {
        LOGGER.info("[GET CONSUMPTIONS BY APARTMENT NAME CONTROLLER] REQUEST RECEIVED");

        final List<Consumption> consumptions =
                consumptionService.getConsumptionsByApartmentName(apartmentName);

        return new ResponseEntity<>(consumptions, HttpStatus.OK);
    }

    @DeleteMapping("/consumption/{consumption-id}")
    public final ResponseEntity<Void> deleteConsumptionById(
            @PathVariable("consumption-id") Integer consumptionId) {
        LOGGER.info("[DELETE CONSUMPTION BY ID CONTROLLER] REQUEST RECEIVED");

        consumptionService.deleteConsumptionById(consumptionId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Autowired
    public void setConsumptionService(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

}
