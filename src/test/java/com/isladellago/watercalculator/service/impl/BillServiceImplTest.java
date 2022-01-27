package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.domain.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.domain.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.domain.model.bill.Bill;
import com.isladellago.watercalculator.domain.model.bill.BillRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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

        when(billRepository.findByBillDate(any()))
                .thenReturn(Optional.of(bill));

        final Bill billResponse =
                billService.getBillByBillDate(billDate);

        Assert.assertNotNull(billResponse);
        verify(billRepository, times(1))
                .findByBillDate(any());
    }

    @Test(expected = ResponseStatusException.class)
    public void testGetBillByBillDateIsNotOk() {
        final String billDate = "15/05/2021 - 15/07/2021";

        when(billRepository.findByBillDate(any()))
                .thenReturn(Optional.empty());

        billService.getBillByBillDate(billDate);
    }

    @Test
    public void testGetBillsIsOk() {
        final Bill bill = new Bill();
        final List<Bill> bills = new ArrayList<>();
        bills.add(bill);

        when(billRepository.findAll())
                .thenReturn(bills);

        final List<Bill> response =
                billService.getAllBills();

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.size());
        verify(billRepository, times(1))
                .findAll();
    }

    @Test
    public final void testDeleteBillByBillDateIsOK() {
        doNothing().when(billRepository)
                .deleteByBillDate(any());

        billService.deleteBillByBillDate("15/05/2021 - 15/07/2021");

        verify(billRepository, times(1))
                .deleteByBillDate(any());
    }
}
