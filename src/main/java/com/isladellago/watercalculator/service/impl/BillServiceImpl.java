package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.model.bill.BillRepository;
import com.isladellago.watercalculator.service.BillService;
import com.isladellago.watercalculator.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BillServiceImpl implements BillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

    private BillRepository billRepository;

    @Override
    public CreateBillRequestResponseDto createBill(CreateBillRequestBodyDto createBillRequestBodyDto) {
        final String methodFormatName = "[CREATE BILL]";
        LOGGER.info(methodFormatName + " METHOD START, CREATE BILL REQUEST DTO: {}",
                JacksonUtils.getJsonStringFromObject(createBillRequestBodyDto));

        final Bill bill = mapBill(createBillRequestBodyDto);
        final String billDate = billRepository.save(bill).getBillDate();

        LOGGER.info(methodFormatName + " METHOD END, BILL DATE: {}", billDate);

        return new CreateBillRequestResponseDto(billDate);
    }

    /**
     * Method to create a new bill entity to be saved on database.
     *
     * @param createBillRequestBodyDto Class that holds data to map a new Bill.
     * @return Bill entity created.
     */
    private Bill mapBill(CreateBillRequestBodyDto createBillRequestBodyDto) {
        final String methodFormatName = "[MAP BILL]";
        LOGGER.info(methodFormatName + " METHOD START");

        final Bill bill = new Bill();
        bill.setBillDate(createBillRequestBodyDto.getBillDate());
        bill.setM3RsdBsc(createBillRequestBodyDto.getM3RsdBsc());
        bill.setM3RsdBscSup(createBillRequestBodyDto.getM3RsdBscSup());
        bill.setDiscounts(createBillRequestBodyDto.getDiscounts());
        bill.setAcueFijoResd(createBillRequestBodyDto.getAcueFijoResd());
        bill.setAcueRsdBsc(createBillRequestBodyDto.getAcueRsdBsc());
        bill.setAcueRsdBscSup(createBillRequestBodyDto.getAcueRsdBscSup());
        bill.setAlcFijoResd(createBillRequestBodyDto.getAlcFijoResd());
        bill.setAlcRsdBsc(createBillRequestBodyDto.getAlcRsdBsc());
        bill.setAlcRsdBscSup(createBillRequestBodyDto.getAlcRsdBscSup());
        bill.setCleaning(createBillRequestBodyDto.getCleaning());

        LOGGER.info(methodFormatName + " METHOD END, FINAL BILL: {}",
                JacksonUtils.getJsonStringFromObject(bill));

        return bill;
    }

    @Autowired
    public void setBillRepository(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}