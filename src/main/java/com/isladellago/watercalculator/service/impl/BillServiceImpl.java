package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.bill.CreateBillRequestBodyDto;
import com.isladellago.watercalculator.dto.bill.CreateBillRequestResponseDto;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.model.bill.BillRepository;
import com.isladellago.watercalculator.service.BillService;
import com.isladellago.watercalculator.utils.JacksonUtils;
import com.isladellago.watercalculator.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

    private BillRepository billRepository;

    @Override
    public CreateBillRequestResponseDto createBill(CreateBillRequestBodyDto createBillRequestBodyDto) {
        final var methodFormatName = "[CREATE BILL]";
        LOGGER.info(methodFormatName + " METHOD START, CREATE BILL REQUEST DTO: {}",
                JacksonUtils.getJsonStringFromObject(createBillRequestBodyDto));

        final var bill = mapBill(createBillRequestBodyDto);
        final var billDate = billRepository.save(bill).getBillDate();

        LOGGER.info(methodFormatName + " METHOD END, BILL DATE: {}", billDate);

        return new CreateBillRequestResponseDto(billDate);
    }

    @Override
    public Bill getBillByBillDate(String billDate) {
        final var methodFormatName = "[GET BILL BY BILL DATE]";
        LOGGER.info(methodFormatName + " METHOD START, BILL DATE: {}",
                billDate);

        final Optional<Bill> optionalBill =
                billRepository.findByBillDate(billDate);

        final var errorMessage =
                String.format("BILL WITH BILL DATE: %s NOT FOUND", billDate);
        final var bill =
                Utilities.validateOptionalResponse(methodFormatName, errorMessage, optionalBill);

        LOGGER.info(methodFormatName + " METHOD END, BILL DETAIL: {}",
                JacksonUtils.getJsonStringFromObject(bill));

        return bill;
    }

    @Override
    public List<Bill> getAllBills() {
        final var methodFormatName = "[GET ALL BILLS]";
        LOGGER.info(methodFormatName + " METHOD START");

        final List<Bill> bills =
                billRepository.findAll();

        LOGGER.info(methodFormatName + " METHOD END, BILLS: {}",
                JacksonUtils.getJsonStringFromObject(bills));

        return bills;
    }

    @Override
    public void deleteBillByBillDate(String billDate) {
        final var methodFormatName = "[DELETE BILL BY BILL DATE]";
        LOGGER.info(methodFormatName + " METHOD START, BILL DATE: {}", billDate);

        billRepository.deleteByBillDate(billDate);

        LOGGER.info(methodFormatName + " METHOD END");
    }

    @Override
    public Bill getPreviousBill(Bill currentBill) {
        LOGGER.info("[GET PREVIOUS BILL] METHOD START, CURRENT BILL: {}",
                JacksonUtils.getJsonStringFromObject(currentBill));

        final var previousBill =
                billRepository.getById(currentBill.getBillId());

        LOGGER.info("[GET PREVIOUS BILL] METHOD END, PREVIOUS BILL: {}",
                JacksonUtils.getJsonStringFromObject(previousBill));

        return previousBill;
    }

    /**
     * Method to create a new bill entity to be saved on database.
     *
     * @param createBillRequestBodyDto Class that holds data to map a new Bill.
     * @return Bill entity created.
     */
    private Bill mapBill(CreateBillRequestBodyDto createBillRequestBodyDto) {
        final var methodFormatName = "[MAP BILL]";
        LOGGER.info(methodFormatName + " METHOD START");

        final var bill = new Bill();
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
