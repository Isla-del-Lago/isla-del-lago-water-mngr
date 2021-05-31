package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;

public interface BillService {

    /**
     * This method saves a new bill on database.
     *
     * @param createBillRequestBodyDto Body received from request.
     * @return Bill date.
     */
    CreateBillRequestResponseDto createBill(CreateBillRequestBodyDto createBillRequestBodyDto);

}
