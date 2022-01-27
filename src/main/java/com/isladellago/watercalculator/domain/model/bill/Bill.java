package com.isladellago.watercalculator.domain.model.bill;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is responsible for map the Bill entity on
 * isla_del_lago database.
 */
@Entity
@Table(name = "bill")
public final class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "bill_date")
    private String billDate;

    @Column(name = "m3_rsd_bsc")
    private int m3RsdBsc;

    @Column(name = "m3_rsd_bsc_sup")
    private int m3RsdBscSup;

    @Column(name = "discounts")
    private int discounts;

    @Column(name = "acue_fijo_resd")
    private double acueFijoResd;

    @Column(name = "acue_rsd_bsc")
    private double acueRsdBsc;

    @Column(name = "acue_rsd_bsc_sup")
    private double acueRsdBscSup;

    @Column(name = "alc_fijo_resd")
    private double alcFijoResd;

    @Column(name = "alc_rsd_bsc")
    private double alcRsdBsc;

    @Column(name = "alc_rsd_bsc_sup")
    private double alcRsdBscSup;

    @Column(name = "cleaning")
    private int cleaning;

    @Column(name = "creation_date")
    private Date creationDate = new Date();

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
