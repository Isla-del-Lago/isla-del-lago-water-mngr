package com.isladellago.watercalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/water")
@CrossOrigin("*")
public class HealthController {

    /**
     * Method responsible for response on health check request.
     *
     * @return
     */
    @GetMapping("/health")
    public final ResponseEntity<String> health() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Service is healthy");
    }

}
