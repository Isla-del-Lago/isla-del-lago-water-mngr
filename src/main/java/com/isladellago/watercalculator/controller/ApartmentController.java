package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.apartment.GetApartmentDetailsResponseDto;
import com.isladellago.watercalculator.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("water")
@CrossOrigin("*")
public class ApartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentController.class);

    private ApartmentService apartmentService;

    @PostMapping("/apartment")
    public final ResponseEntity<GetApartmentDetailsResponseDto> saveConsumptionDetails(
            @RequestParam("billDate") String billDate) {
        LOGGER.info("[GET APARTMENT DETAILS CONTROLLER] REQUEST RECEIVED");

        apartmentService.saveConsumptionDetails(billDate);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Autowired
    public void setApartmentService(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }
}
