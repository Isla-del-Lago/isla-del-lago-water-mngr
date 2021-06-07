package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.dto.consumption.CreateConsumptionRequestBodyDto;
import com.isladellago.watercalculator.dto.consumption.CreateConsumptionResponseDto;
import com.isladellago.watercalculator.model.consumption.Consumption;
import com.isladellago.watercalculator.model.consumption.ConsumptionRepository;
import com.isladellago.watercalculator.service.ConsumptionService;
import com.isladellago.watercalculator.utils.JacksonUtils;
import com.isladellago.watercalculator.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionServiceImpl.class);

    private ConsumptionRepository consumptionRepository;

    @Override
    public CreateConsumptionResponseDto createConsumption(CreateConsumptionRequestBodyDto consumptionRequestBodyDto) {
        LOGGER.info("[CREATE CONSUMPTION] METHOD START, CREATE CONSUMPTION BODY DTO: {}",
                JacksonUtils.getJsonStringFromObject(consumptionRequestBodyDto));

        final Consumption consumption = mapConsumption(consumptionRequestBodyDto);
        final Integer consumptionId = consumptionRepository.save(consumption).getConsumptionId();

        LOGGER.info("[CREATE CONSUMPTION] METHOD END, CONSUMPTION ID: {}", consumptionId);

        return new CreateConsumptionResponseDto(consumptionId);
    }

    @Override
    public List<Consumption> getAllConsumptions() {
        LOGGER.info("[GET ALL CONSUMPTIONS] METHOD START");

        final List<Consumption> consumptions = consumptionRepository.findAll();

        LOGGER.info("[GET ALL CONSUMPTIONS] METHOD END, CONSUMPTIONS: {}",
                JacksonUtils.getJsonStringFromObject(consumptions));

        return consumptions;
    }

    @Override
    public Consumption getConsumptionById(Integer id) {
        final String methodFormatName = "[GET CONSUMPTION BY ID]";
        LOGGER.info(methodFormatName + " METHOD START, CONSUMPTION ID: {}", id);

        final Optional<Consumption> consumptionOptional = consumptionRepository.findById(id);

        final String errorMessage = String.format("CONSUMPTION WITH ID: %d NOT FOUND", id);
        final Consumption consumption =
                Utilities.validateOptionalResponse(methodFormatName, errorMessage, consumptionOptional);

        LOGGER.info(methodFormatName + " METHOD END, CONSUMTPION: {}",
                JacksonUtils.getJsonStringFromObject(consumption));

        return consumption;
    }

    @Override
    public List<Consumption> getConsumptionsByBillDate(String billDate) {
        final String methodFormatName = "[GET CONSUMPTIONS BY BILL DATE]";
        LOGGER.info(methodFormatName + " METHOD START, BILL DATE: {}", billDate);

        final Optional<List<Consumption>> optionalConsumptionList =
                consumptionRepository.findAllByBillDate(billDate);

        final String errorMessage = String.format("NO CONSUMPTIONS FOUND FOR BILL DATE: %s", billDate);
        final List<Consumption> consumptions =
                Utilities.validateOptionalResponse(methodFormatName, errorMessage, optionalConsumptionList);

        LOGGER.info(methodFormatName + " METHOD END, CONSUMPTIONS: {}",
                JacksonUtils.getJsonStringFromObject(consumptions));

        return consumptions;
    }

    @Override
    public List<Consumption> getConsumptionsByApartmentName(String apartmentName) {
        final String methodFormatName = "[GET CONSUMPTIONS BY APARTMENT NAME]";
        LOGGER.info(methodFormatName + " METHOD START, APARTMENT NAME: {}", apartmentName);

        final Optional<List<Consumption>> optionalConsumptionList =
                consumptionRepository.findAllByApartmentName(apartmentName);

        final String errorMessage = String.format("NO CONSUMPTIONS FOUND FOR APARTMENT NAME: %s",
                apartmentName);
        final List<Consumption> consumptions =
                Utilities.validateOptionalResponse(methodFormatName, errorMessage, optionalConsumptionList);

        LOGGER.info(methodFormatName + "METHOD END, CONSUMPTIONS: {}",
                JacksonUtils.getJsonStringFromObject(consumptions));

        return consumptions;
    }

    @Override
    public void deleteConsumptionById(Integer consumptionId) {
        final String methodFormatName = "[DELETE CONSUMPTION BY ID]";
        LOGGER.info(methodFormatName + " METHOD START, CONSUMPTION ID: {}", consumptionId);

        consumptionRepository.deleteById(consumptionId);

        LOGGER.info(methodFormatName + " METHOD END");
    }

    @Override
    public Consumption getPreviousConsumption(Consumption currentConsumption) {
        LOGGER.info("[GET PREVIOUS CONSUMPTION] METHOD START, CURRENT CONSUMPTION: {}",
                JacksonUtils.getJsonStringFromObject(currentConsumption));

        final List<Consumption> consumptions =
                consumptionRepository
                        .findAllByApartmentName(currentConsumption.getApartmentName()).get();

        final Consumption previousConsumption =
                consumptions
                        .stream()
                        .filter(consumption -> consumption
                                .getConsumptionId()
                                .equals(currentConsumption.getConsumptionId() - 10))
                        .findFirst().get();

        LOGGER.info("[GET PREVIOUS CONSUMPTION] METHOD END, PREVIOUS CONSUMPTION: {}",
                JacksonUtils.getJsonStringFromObject(previousConsumption));

        return previousConsumption;
    }

    /**
     * This method maps the create consumption request body to a new Consumption
     * entity to be saved on DB.
     *
     * @param createConsumptionRequestBodyDto Request body.
     * @return Consumption mapped.
     */
    private Consumption mapConsumption(CreateConsumptionRequestBodyDto createConsumptionRequestBodyDto) {
        LOGGER.info("[MAP CONSUMPTION] METHOD START");

        final Consumption consumption = new Consumption();
        consumption.setApartmentName(createConsumptionRequestBodyDto.getApartmentName());
        consumption.setBillDate(createConsumptionRequestBodyDto.getBillDate());
        consumption.setMeterValue(createConsumptionRequestBodyDto.getMeterValue());
        consumption.setValuePhotoUrl(createConsumptionRequestBodyDto.getValuePhotoUrl());

        LOGGER.info("[MAP CONSUMPTION] METHOD END, FINAL CONSUMPTION: {}",
                JacksonUtils.getJsonStringFromObject(consumption));

        return consumption;
    }

    @Autowired
    public void setConsumptionRepository(ConsumptionRepository consumptionRepository) {
        this.consumptionRepository = consumptionRepository;
    }
}
