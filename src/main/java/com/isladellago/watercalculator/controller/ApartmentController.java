package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("water")
@CrossOrigin("*")
public class ApartmentController {

    private ApartmentService apartmentService;



    @Autowired
    public void setApartmentService(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }
}
