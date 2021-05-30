package com.isladellago.watercalculator.dto.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class responsible for map the create consumption request response.
 */
public final class CreateConsumptionResponseDto {

    private int consumptionId;

    public CreateConsumptionResponseDto(int consumptionId) {
        this.consumptionId = consumptionId;
    }

    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
    }
}
