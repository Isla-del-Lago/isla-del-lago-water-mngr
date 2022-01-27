package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.domain.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.domain.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.domain.model.bill.Bill;
import com.isladellago.watercalculator.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/bill/bill-date")
    public final ResponseEntity<Bill> getBillByBillDate(@RequestParam("billDate") String billDate) {
        LOGGER.info("[GET BILL BY DATE CONTROLLER] REQUEST RECEIVED");

        final var bill = billService.getBillByBillDate(billDate);

        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping("/bill")
    public final ResponseEntity<List<Bill>> getAllBills() {
        LOGGER.info("[GET ALL BILLS CONTROLLER] REQUEST RECEIVED");

        final List<Bill> bills =
                billService.getAllBills();

        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @DeleteMapping("/bill/bill-date/{bill-date}")
    public final ResponseEntity<Void> deleteBillByBillDate(
            @PathVariable("bill-date") String billDate) {
        LOGGER.info("[DELETE BILL BY BILL DATE CONTOLLER] REQUEST RECEIVED");

        billService.deleteBillByBillDate(billDate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

}
