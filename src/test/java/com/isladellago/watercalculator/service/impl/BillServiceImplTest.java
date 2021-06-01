package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.model.bill.BillRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public final class BillServiceImplTest {

    private static final int METHOD_CALL_ONE_TIME = 1;
    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBillIsOk() {
        final CreateBillRequestBodyDto createBillRequestBodyDto =
                new CreateBillRequestBodyDto();

        final String billDate = "15/05/2021 - 15/07/2021";
        final Bill bill = new Bill();
        bill.setBillDate(billDate);

        when(billRepository.save(any())).thenReturn(bill);

        CreateBillRequestResponseDto response =
                billService.createBill(createBillRequestBodyDto);

        Assert.assertNotNull(response);
        Assert.assertEquals(billDate, response.getBillDate());
        verify(billRepository, times(METHOD_CALL_ONE_TIME)).save(any());
    }

    @Test
    public void testGetBillByBillDateIsOk() {
        final Bill bill = new Bill();
        final String billDate = "15/05/2021 - 15/07/2021";

        when(billRepository.findById(any()))
                .thenReturn(Optional.of(bill));

        final Bill billResponse =
                billService.getBillByBillDate(billDate);

        Assert.assertNotNull(billResponse);
        verify(billRepository, times(1))
                .findById(any());
    }

    @Test(expected = ResponseStatusException.class)
    public void testGetBillByBillDateIsNotOk() {
        final String billDate = "15/05/2021 - 15/07/2021";

        when(billRepository.findById(any()))
                .thenReturn(Optional.empty());

        billService.getBillByBillDate(billDate);
    }
}
