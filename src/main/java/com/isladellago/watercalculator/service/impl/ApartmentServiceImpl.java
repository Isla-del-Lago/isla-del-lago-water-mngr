package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.consumptiondetail.ConsumptionDetailResponse;
import com.isladellago.watercalculator.model.bill.Bill;
import com.isladellago.watercalculator.model.consumption.*;
import com.isladellago.watercalculator.model.consumptiondetail.*;
import com.isladellago.watercalculator.service.ApartmentService;
import com.isladellago.watercalculator.service.BillService;
import com.isladellago.watercalculator.service.ConsumptionService;
import com.isladellago.watercalculator.utils.JacksonUtils;
import com.isladellago.watercalculator.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private static final String[] APT_NAMES = {
            "Apto 201", "Apto 202", "Apto 301", "Apto 302", "Apto 401",
            "Apto 402", "Apto 501", "Apto 502", "Local 1", "Local 2"
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentServiceImpl.class);
    private static final double NUMBER_OF_APARTMENTS = 10.0;

    private ConsumptionService consumptionService;
    private BillService billService;
    private ConsumptionDetailRepository consumptionDetailRepository;

    @Override
    public void saveConsumptionDetails(String billDate) {
        LOGGER.info("[SAVE CONSUMPTION DETAILS] METHOD START, BILL DATE: {}",
                billDate);

        final Bill bill = billService.getBillByBillDate(billDate);
        final List<Consumption> consumptions =
                consumptionService.getConsumptionsByBillDate(billDate);

        if (consumptions.size() != NUMBER_OF_APARTMENTS) {
            LOGGER.error("[SAVE CONSUMPTION DETAILS] THERE ARE CONSUMPTIONS LEFT, BILL DATE: {}, CONSUMPTIONS: {}",
                    billDate, JacksonUtils.getJsonStringFromObject(consumptions));
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        calculateAndSaveConsumptionDetails(consumptions, bill);

        LOGGER.info("[SAVE CONSUMPTION DETAILS] METHOD END");
    }

    @Override
    public ConsumptionDetailResponse getConsumptionDetailFromAptNameAndBillDate(String aptName, String billDate) {
        final String methodFormatName = "[GET CONSUMPTION DETAIL FROM APT NAME AND BILL DATE]";
        LOGGER.info(methodFormatName + " METHOD START, APT NAME: {}, BILL DATE: {}",
                aptName, billDate);

        final Optional<ConsumptionDetail> optionalConsumptionDetail =
                consumptionDetailRepository.findByAptNameAndBillDate(aptName, billDate);

        final String errorMessage =
                String.format("CONSUMPTION DETAIL NOT FOUND WITH APT NAME: %s, BILL DATE: %s", aptName, billDate);
        final ConsumptionDetail consumptionDetail =
                Utilities.validateOptionalResponse(methodFormatName, errorMessage, optionalConsumptionDetail);

        final ConsumptionDetailResponse consumptionDetailResponse =
                mapConsumptionDetailResponse(consumptionDetail);

        LOGGER.info(methodFormatName + " METHOD END, CONSUMPTION DETAIL: {}",
                JacksonUtils.getJsonStringFromObject(consumptionDetailResponse));

        return consumptionDetailResponse;
    }

    @Override
    public List<ConsumptionDetailResponse> getAllConsumptionDetails() {
        LOGGER.info("[GET ALL CONSUMPTION DETAILS] METHOD START");

        final List<ConsumptionDetailResponse> consumptionDetailResponses =
                new ArrayList<>();

        Arrays.asList(APT_NAMES).stream().forEach(aptName -> {
            final List<ConsumptionDetailResponse> consumptionDetailsFromAptName =
                    getAllConsumptionDetailsFromAptName(aptName);

            consumptionDetailResponses.addAll(consumptionDetailsFromAptName);
        });

        LOGGER.info("[GET ALL CONSUMPTION DETAILS] METHOD END, CONSUMPTION DETAILS: {}",
                JacksonUtils.getJsonStringFromObject(consumptionDetailResponses));

        return consumptionDetailResponses;
    }

    @Override
    public List<ConsumptionDetailResponse> getAllConsumptionDetailsFromBillDate(String billDate) {
        LOGGER.info("[GET ALL CONSUMPTIONS DETAILS FROM BILL DATE] METHOD START, BILL DATE: {}",
                billDate);

        final List<ConsumptionDetailResponse> consumptionDetails =
                consumptionDetailRepository
                        .findAllByBillDate(billDate)
                        .stream()
                        .map(this::mapConsumptionDetailResponse)
                        .collect(Collectors.toList());

        LOGGER.info("[GET ALL CONSUMPTIONS DETAILS FROM BILL DATE] METHOD END, CONSUMPTION DETAILS: {}",
                JacksonUtils.getJsonStringFromObject(consumptionDetails));

        return consumptionDetails;
    }

    @Override
    public List<ConsumptionDetailResponse> getAllConsumptionDetailsFromAptName(String aptName) {
        LOGGER.info("GET ALL CONSUMPTIONS DETAILS FROM APT NAME] METHOD START, APT NAME: {}",
                aptName);

        final List<ConsumptionDetailResponse> consumptionDetails =
                consumptionDetailRepository
                        .findAllByAptName(aptName)
                        .stream()
                        .map(this::mapConsumptionDetailResponse)
                        .collect(Collectors.toList());

        LOGGER.info("GET ALL CONSUMPTIONS DETAILS FROM APT NAME] METHOD END, CONSUMPTION DETAILS: {}",
                consumptionDetails);

        return consumptionDetails;
    }

    /**
     * This method maps a consumption detail response from the
     * given consumption detail.
     *
     * @param consumptionDetail Consumption detail.
     * @return Consumption detail response.
     */
    private ConsumptionDetailResponse mapConsumptionDetailResponse(ConsumptionDetail consumptionDetail) {
        final ConsumptionDetailResponse consumptionDetailResponse =
                new ConsumptionDetailResponse();

        final int consumptionId = consumptionDetail.getConsumptionId();

        final String aptName =
                consumptionDetailRepository.getApartmentNameFromConsumptionId(consumptionId);

        final String billDate =
                consumptionDetailRepository.getBillDateFromConsumptionId(consumptionId);

        consumptionDetailResponse.setApartmentName(aptName);
        consumptionDetailResponse.setBillDate(billDate);
        consumptionDetailResponse.setConsumptionDetail(consumptionDetail);

        return consumptionDetailResponse;
    }

    /**
     * This method is responsible for calculate all consumption
     * details for all apartments.
     *
     * @param consumptions All consumptions.
     * @param bill         Bill detail.
     * @return Map with apartments details.
     */
    private void calculateAndSaveConsumptionDetails(List<Consumption> consumptions, Bill bill) {
        LOGGER.info("[CALCULATE AND SAVE CONSUMPTION DETAILS] METHOD START");

        final Map<Consumption, Consumption> previousAndCurrentConsumptions =
                getPreviousConsumptions(consumptions);

        final double totalMeterValues =
                getTotalDifferenceMeterValues(previousAndCurrentConsumptions);

        for (Map.Entry<Consumption, Consumption> entry : previousAndCurrentConsumptions.entrySet()) {
            final Consumption currentConsumption = entry.getKey();
            final Consumption previousConsumption = entry.getValue();

            final String apartmentName = currentConsumption.getApartmentName();
            LOGGER.info("[CALCULATE APARTMENT DETAILS START] APARTMENT NAME: {}", apartmentName);

            final double consumptionPercentage =
                    calculateConsumptionPercentage(currentConsumption, previousConsumption, totalMeterValues);

            final ConsumptionDetail consumptionDetail =
                    calculateConsumptionDetail(consumptionPercentage, bill,
                            apartmentName, currentConsumption.getConsumptionId());

            consumptionDetailRepository.save(consumptionDetail);
        }

        LOGGER.info("[CALCULATE AND SAVE CONSUMPTION DETAILS] METHOD END");
    }

    /**
     * This method calculates the total from the difference
     * between meter values from previous and current
     * consumptions.
     *
     * @param previousAndCurrentConsumptions Map with previous and current consumptions.
     * @return Total difference calculated
     */
    private double getTotalDifferenceMeterValues(
            Map<Consumption, Consumption> previousAndCurrentConsumptions) {
        LOGGER.info("[GET TOTAL DIFFERENCE METER VALUES] METHOD START, CONSUMPTIONS: {}",
                JacksonUtils.getJsonStringFromObject(previousAndCurrentConsumptions));

        double totalDifference = 0;

        for (Map.Entry<Consumption, Consumption> entry : previousAndCurrentConsumptions.entrySet()) {
            final Consumption currentConsumption = entry.getKey();
            final Consumption previousConsumption = entry.getValue();

            final double previousMeterValue =
                    getDoubleValueFromMeterValue(previousConsumption.getMeterValue());
            final double currentMeterValue =
                    getDoubleValueFromMeterValue(currentConsumption.getMeterValue());

            totalDifference += (currentMeterValue - previousMeterValue);
        }

        LOGGER.info("[GET TOTAL DIFFERENCE METER VALUES] METHOD END, TOTAL: {}",
                totalDifference);

        return totalDifference;
    }

    /**
     * This method craetes a HashMap with the current consumpiton
     * and its previous consumption.
     *
     * @param consumptions Current consumptions.
     * @return Difference total as hash map.
     */
    private Map<Consumption, Consumption> getPreviousConsumptions(List<Consumption> consumptions) {
        final Map<Consumption, Consumption> previousAndCurrentConsumptions =
                new HashMap<>();

        consumptions.forEach(currentConsumption -> {
            final Consumption previousConsumption =
                    consumptionService.getPreviousConsumption(currentConsumption);

            previousAndCurrentConsumptions.put(currentConsumption, previousConsumption);
        });

        return previousAndCurrentConsumptions;
    }

    /**
     * Method responsible to calculate all necessary
     * fields to create an apartment detail.
     *
     * @param consumptionPercentage Consumption percentage from the apartment.
     * @param bill                  Bill detail.
     * @param apartmentName         Apartment name.
     * @return Apartment detail.
     */
    private ConsumptionDetail calculateConsumptionDetail(double consumptionPercentage, Bill bill,
                                                         String apartmentName, int consumptionId) {
        LOGGER.info("[CALCULATE APARTMENT DETAIL] METHOD START, APARTMENT NAME: {}",
                apartmentName);

        final ConsumptionDetail consumptionDetail = new ConsumptionDetail();

        final CubicMetersDetail cubicMetersDetail =
                getCubicMetersDetail(consumptionPercentage, consumptionId, bill);
        cubicMetersDetail.setConsumptionDetail(consumptionDetail);

        final double m3ResidentialBasic =
                cubicMetersDetail.getM3ResidentialBasic();
        final double m3ResidentialBasicSuperior =
                cubicMetersDetail.getM3ResidentialBasicSuperior();

        final AcueductoDetail acueductoDetail =
                getAcueductoDetail(m3ResidentialBasic, m3ResidentialBasicSuperior, bill, consumptionId);
        acueductoDetail.setConsumptionDetail(consumptionDetail);

        final AlcantarilladoDetail alcantarilladoDetail =
                getAlcantarilladoDetail(m3ResidentialBasic, m3ResidentialBasicSuperior, bill, consumptionId);
        alcantarilladoDetail.setConsumptionDetail(consumptionDetail);

        final double discounts = bill.getDiscounts() / NUMBER_OF_APARTMENTS;
        final double cleaning = bill.getCleaning() / NUMBER_OF_APARTMENTS;

        final double totalAcueductoAndAlcantarillado =
                calculateTotalAcueductoAndAlcantarillado(acueductoDetail, alcantarilladoDetail);
        final double total =
                totalAcueductoAndAlcantarillado + cleaning - discounts;

        consumptionDetail.setConsumptionId(consumptionId);
        consumptionDetail.setCubicMetersDetail(cubicMetersDetail);
        consumptionDetail.setAcueductoDetail(acueductoDetail);
        consumptionDetail.setAlcantarilladoDetail(alcantarilladoDetail);
        consumptionDetail.setDiscount(discounts);
        consumptionDetail.setCleaning(cleaning);
        consumptionDetail.setTotal(total);

        LOGGER.info("[CALCULATE APARTMENT DETAIL] METHOD END, APARTMENT DETAIL: {}",
                JacksonUtils.getJsonStringFromObject(consumptionDetail));

        return consumptionDetail;
    }

    /**
     * This method maps the cubic meter detail fields.
     *
     * @param consumptionPercentage Consumption percentage from the apartment.
     * @param bill                  Current bill.
     * @param consumptionId         Consumption id.
     * @return Details mapped.
     */
    private CubicMetersDetail getCubicMetersDetail(double consumptionPercentage, int consumptionId,
                                                   Bill bill) {
        final CubicMetersDetail cubicMetersDetail = new CubicMetersDetail();

        final double m3ResidentialBasic =
                bill.getM3RsdBsc() * (consumptionPercentage / 100);
        final double m3ResidentialBasicSup =
                bill.getM3RsdBscSup() * (consumptionPercentage / 100);

        cubicMetersDetail.setConsumptionId(consumptionId);
        cubicMetersDetail.setM3ResidentialBasic(m3ResidentialBasic);
        cubicMetersDetail.setM3ResidentialBasicSuperior(m3ResidentialBasicSup);
        cubicMetersDetail.setTotalM3Consumed(m3ResidentialBasic + m3ResidentialBasicSup);

        return cubicMetersDetail;
    }

    /**
     * This method calculates the total value from acueducto and
     * alcantarillado.
     *
     * @param acueductoDetail
     * @param alcantarilladoDetail
     * @return
     */
    private double calculateTotalAcueductoAndAlcantarillado(AcueductoDetail acueductoDetail,
                                                            AlcantarilladoDetail alcantarilladoDetail) {
        final double totalAcueducto = acueductoDetail.getResidentialBasicSuperiorValue() +
                acueductoDetail.getResidentialValue() + acueductoDetail.getResidentialBasicValue();
        final double totalAlcantarillado = alcantarilladoDetail.getResidentialBasicSuperiorValue() +
                alcantarilladoDetail.getResidentialValue() + alcantarilladoDetail.getResidentialBasicValue();

        return totalAcueducto + totalAlcantarillado;
    }

    /**
     * This method calculates the alcantarillado values for the apartment.
     *
     * @param m3ResidentialBasic         Basic m3 value.
     * @param m3ResidentialBasicSuperior Superior basic m3 value.
     * @param bill                       Bill detail.
     * @return Alcantarillado detail.
     */
    private AlcantarilladoDetail getAlcantarilladoDetail(double m3ResidentialBasic, double m3ResidentialBasicSuperior,
                                                         Bill bill, int consumptionId) {
        LOGGER.info("[GET ALCANTARILLADO DETAIL] METHOD START, CONSUMPTION ID: {}",
                consumptionId);

        final AlcantarilladoDetail detail = new AlcantarilladoDetail();

        detail.setConsumptionId(consumptionId);
        detail.setResidentialValue(0.1d * bill.getAlcFijoResd());
        detail.setResidentialBasicValue(m3ResidentialBasic * bill.getAlcRsdBsc());
        detail.setResidentialBasicSuperiorValue(m3ResidentialBasicSuperior * bill.getAlcRsdBscSup());

        LOGGER.info("[GET ALCANTARILLADO DETAIL] METHOD END, DETAIL: {}",
                JacksonUtils.getJsonStringFromObject(detail));

        return detail;
    }

    /**
     * This method calculates the acueducto values for the apartment.
     *
     * @param m3ResidentialBasic         Basic m3 value.
     * @param m3ResidentialBasicSuperior Superior basic m3 value.
     * @param bill                       Bill detail.
     * @return Acueducto detail.
     */
    private AcueductoDetail getAcueductoDetail(double m3ResidentialBasic, double m3ResidentialBasicSuperior,
                                               Bill bill, int consumptionId) {
        LOGGER.info("[GET ACUEDUCTO DETAIL] METHOD START");

        final AcueductoDetail detail = new AcueductoDetail();

        detail.setConsumptionId(consumptionId);
        detail.setResidentialValue(0.1d * bill.getAcueFijoResd());
        detail.setResidentialBasicValue(m3ResidentialBasic * bill.getAcueRsdBsc());
        detail.setResidentialBasicSuperiorValue(m3ResidentialBasicSuperior * bill.getAcueRsdBscSup());

        LOGGER.info("[GET ACUEDUCTO DETAIL] METHOD END, DETAIL: {}",
                JacksonUtils.getJsonStringFromObject(detail));

        return detail;
    }

    /**
     * This method calculates the consumption percentage
     * based on two consumptions.
     *
     * @param currentConsumption  Current consumption.
     * @param previousConsumption Previous consumption.
     * @return Percentage calculated
     */
    private double calculateConsumptionPercentage(Consumption currentConsumption, Consumption previousConsumption,
                                                  double totalMeterValues) {
        LOGGER.info("[CALCULATE CONSUMPTION PERCENTAGE] METHOD START");

        final double previousMeterValue =
                getDoubleValueFromMeterValue(previousConsumption.getMeterValue());
        final double currentMeterValue =
                getDoubleValueFromMeterValue(currentConsumption.getMeterValue());

        final double percentage =
                ((currentMeterValue - previousMeterValue) / totalMeterValues) * 100;

        LOGGER.info("[CALCULATE CONSUMPTION PERCENTAGE] METHOD END, PERCENTAGE: {}",
                percentage);

        return percentage;
    }

    /**
     * Converts the given meter value to Double.
     *
     * @param meterValue MEter value as String.
     * @return Meter value as Double.
     */
    private double getDoubleValueFromMeterValue(String meterValue) {
        return Double.parseDouble(meterValue.replace(",", "."));
    }

    @Autowired
    public void setConsumptionService(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @Autowired
    public void setConsumptionDetailRepository(ConsumptionDetailRepository consumptionDetailRepository) {
        this.consumptionDetailRepository = consumptionDetailRepository;
    }
}
