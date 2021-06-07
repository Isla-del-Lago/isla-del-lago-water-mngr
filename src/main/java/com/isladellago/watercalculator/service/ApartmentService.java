package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.dto.apartment.GetApartmentDetailsResponseDto;

public interface ApartmentService {

    /**
     * This method map all necessary values to show usefull
     * information about apartments consumpitons.
     *
     * @param billDate Bill date which consumptions will
     *                 be calculated.
     * @return Consumptions details from the given bill date.
     */
    void saveConsumptionDetails(String billDate);
}
