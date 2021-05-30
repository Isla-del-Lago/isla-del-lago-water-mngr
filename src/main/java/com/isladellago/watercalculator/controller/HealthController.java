package com.isladellago.watercalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/water")
@CrossOrigin("*")
public class HealthController {

    /**
     * Method responsible for response on health check request.
     *
     * @return
     */
    @GetMapping("/health")
    public final ResponseEntity<String> health() {
        final String healthMessageBody = "Is healthy";
        return new ResponseEntity(healthMessageBody, HttpStatus.OK);
    }

}
