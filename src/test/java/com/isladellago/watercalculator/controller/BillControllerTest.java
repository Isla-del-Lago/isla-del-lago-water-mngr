package com.isladellago.watercalculator.controller;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.service.BillService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public final class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBillIsOk() {
        final String billDate = "15/05/2020 - 15/07/2020";
        final CreateBillRequestResponseDto createBillRequestResponseDto =
                new CreateBillRequestResponseDto(billDate);

        when(billService.createBill(any())).thenReturn(createBillRequestResponseDto);

        final ResponseEntity<CreateBillRequestResponseDto> response =
                billController.createBill(new CreateBillRequestBodyDto());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(billDate, response.getBody().getBillDate());
    }

    @Test(expected = ResponseStatusException.class)
    public void testCreateBillIsNotOk() {
        when(billService.createBill(any()))
                .thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        billController.createBill(new CreateBillRequestBodyDto());
    }

    @Test
    public final void testGetBillByBillDateIsOk() {
        final String billDate = "15/05/2021 - 15/07/2021";

        when(billService.getBillByBillDate(any()))
                .thenReturn(new Bill());

        final ResponseEntity<Bill> response =
                billController.getBillByBillDate(billDate);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody() instanceof Bill);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
