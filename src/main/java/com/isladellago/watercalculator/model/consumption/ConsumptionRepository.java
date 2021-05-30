package com.isladellago.watercalculator.model.consumption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    Optional<List<Consumption>> findAllByBillDate(String billDate);

    Optional<List<Consumption>> findAllByApartmentName(String apartmentName);
}
