package com.isladellago.watercalculator.domain.dto.bill;

/**
 * This class is responsible to map the create bill request response.
 */
public class CreateBillRequestResponseDto {

    private String billDate;

    public CreateBillRequestResponseDto(String billDate) {
        this.billDate = billDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
