package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;

import java.util.List;

public interface BillService {

    /**
     * This method saves a new bill on database.
     *
     * @param createBillRequestBodyDto Body received from request.
     * @return Bill date.
     */
    CreateBillRequestResponseDto createBill(CreateBillRequestBodyDto createBillRequestBodyDto);

    /**
     * This method gets a bill details from a given bill date.
     *
     * @param billDate Bill date the bill will be searched with.
     * @return Bill detail.
     */
    Bill getBillByBillDate(String billDate);

    /**
     * This method returns all bills created on isla_deL_lago
     * database.
     *
     * @return List with bills.
     */
    List<Bill> getAllBills();
}
