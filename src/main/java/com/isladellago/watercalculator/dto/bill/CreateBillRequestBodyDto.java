package com.isladellago.watercalculator.dto.bill;

/**
 * This class is responsible to map the create bill request body.
 */
public final class CreateBillRequestBodyDto {

    private String billDate;
    private int m3RsdBsc;
    private int m3RsdBscSup;
    private int discounts;
    private double acueFijoResd;
    private double acueRsdBsc;
    private double acueRsdBscSup;
    private double alcFijoResd;
    private double alcRsdBsc;
    private double alcRsdBscSup;
    private int cleaning;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getM3RsdBsc() {
        return m3RsdBsc;
    }

    public void setM3RsdBsc(int m3RsdBsc) {
        this.m3RsdBsc = m3RsdBsc;
    }

    public int getM3RsdBscSup() {
        return m3RsdBscSup;
    }

    public void setM3RsdBscSup(int m3RsdBscSup) {
        this.m3RsdBscSup = m3RsdBscSup;
    }

    public int getDiscounts() {
        return discounts;
    }

    public void setDiscounts(int discounts) {
        this.discounts = discounts;
    }

    public double getAcueFijoResd() {
        return acueFijoResd;
    }

    public void setAcueFijoResd(double acueFijoResd) {
        this.acueFijoResd = acueFijoResd;
    }

    public double getAcueRsdBsc() {
        return acueRsdBsc;
    }

    public void setAcueRsdBsc(double acueRsdBsc) {
        this.acueRsdBsc = acueRsdBsc;
    }

    public double getAcueRsdBscSup() {
        return acueRsdBscSup;
    }

    public void setAcueRsdBscSup(double acueRsdBscSup) {
        this.acueRsdBscSup = acueRsdBscSup;
    }

    public double getAlcFijoResd() {
        return alcFijoResd;
    }

    public void setAlcFijoResd(double alcFijoResd) {
        this.alcFijoResd = alcFijoResd;
    }

    public double getAlcRsdBsc() {
        return alcRsdBsc;
    }

    public void setAlcRsdBsc(double alcRsdBsc) {
        this.alcRsdBsc = alcRsdBsc;
    }

    public double getAlcRsdBscSup() {
        return alcRsdBscSup;
    }

    public void setAlcRsdBscSup(double alcRsdBscSup) {
        this.alcRsdBscSup = alcRsdBscSup;
    }

    public int getCleaning() {
        return cleaning;
    }

    public void setCleaning(int cleaning) {
        this.cleaning = cleaning;
    }
}
