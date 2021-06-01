package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is responsible to hold all paths to handle requests
 * over bill entity.
 */
@RestController
@RequestMapping("/water")
@CrossOrigin("*")
public class BillController {

    private BillService billService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);

    @PostMapping("/bill")
    public final ResponseEntity<CreateBillRequestResponseDto> createBill(
            @RequestBody CreateBillRequestBodyDto createBillRequestBodyDto) {
        LOGGER.info("[CREATE BILL CONTROLLER] REQUEST RECEIVED");

        final CreateBillRequestResponseDto createBillResponse =
                billService.createBill(createBillRequestBodyDto);

        return new ResponseEntity<>(createBillResponse, HttpStatus.CREATED);
    }

    @GetMapping("/bill/bill-date/{bill-date}")
    public final ResponseEntity<Bill> getBillByBillDate(@PathVariable("bill-date") String billDate) {
        LOGGER.info("[GET BILL BY DATE CONTROLLER] REQUEST RECEIVED");

        final Bill bill = billService.getBillByBillDate(billDate);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

}
