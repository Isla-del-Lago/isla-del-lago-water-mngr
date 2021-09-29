package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.apartment.GetApartmentDetailsResponseDto;
import com.isladellago.watercalculator.dto.consumptiondetail.ConsumptionDetailResponse;
import com.isladellago.watercalculator.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("water")
@CrossOrigin("*")
public class ApartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentController.class);

    private ApartmentService apartmentService;

    @PostMapping("/apartment")
    public final ResponseEntity<Void> saveConsumptionDetails(
            @RequestParam("billDate") String billDate) {
        LOGGER.info("[GET APARTMENT DETAILS CONTROLLER] REQUEST RECEIVED");

        apartmentService.saveConsumptionDetails(billDate);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/apartment")
    public final ResponseEntity<List<ConsumptionDetailResponse>> getAllConsumptionDetails() {
        LOGGER.info("[GET ALL CONSUMPTION DETAILS] REQUEST RECEIVED");

        final List<ConsumptionDetailResponse> consumptionDetails =
                apartmentService.getAllConsumptionDetails();

        return new ResponseEntity<>(consumptionDetails, HttpStatus.OK);
    }

    @GetMapping("/apartment/bill-date")
    public final ResponseEntity<List<ConsumptionDetailResponse>> getAllConsumptionDetailsFromBillDate(
            @RequestParam("billDate") String billDate) {
        LOGGER.info("[GET ALL CONSUMPTIONS DETAILS FROM BILL DATE] REQUEST RECEIVED");

        final List<ConsumptionDetailResponse> consumptionDetails =
                apartmentService.getAllConsumptionDetailsFromBillDate(billDate);

        return new ResponseEntity<>(consumptionDetails, HttpStatus.OK);
    }

    @GetMapping("apartment/apt-name/{apt-name}")
    public final ResponseEntity<List<ConsumptionDetailResponse>> getAllConsumptionDetailsFromAptName(
            @PathVariable("apt-name") String aptName) {
        LOGGER.info("GET ALL CONSUMPTION DETAILS FROM APT NAME] REQUEST RECEIVED");

        final List<ConsumptionDetailResponse> consumptionDetails =
                apartmentService.getAllConsumptionDetailsFromAptName(aptName);

        return new ResponseEntity<>(consumptionDetails, HttpStatus.OK);
    }

    @GetMapping("/apartment/apt-name/{apt-name}/bill-date")
    public final ResponseEntity<ConsumptionDetailResponse> getConsumptionDetailFromAptNameAndBillDate(
            @PathVariable("apt-name") String aptName, @RequestParam("billDate") String billDate) {
        LOGGER.info("[GET CONSUMPTION DETAIL FROM APT NAME AND BILL DATE] REQUEST RECEIVED");

        final ConsumptionDetailResponse consumptionDetail =
                apartmentService.getConsumptionDetailFromAptNameAndBillDate(aptName, billDate);

        return new ResponseEntity<>(consumptionDetail, HttpStatus.OK);

    }

    @Autowired
    public void setApartmentService(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }
}
