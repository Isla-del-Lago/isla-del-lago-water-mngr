package com.isladellago.watercalculator.domain.model.consumptiondetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumptionDetailRepository extends JpaRepository<ConsumptionDetail, Integer> {

    String GET_CONSUMPTION_DETAILS = "SELECT * FROM consumption_detail cd " +
            "RIGHT JOIN consumption c ON cd.consumption_id = c.consumption_id " +
            "JOIN acueducto_detail acued ON cd.consumption_id = acued.consumption_id " +
            "JOIN alcantarillado_detail alcad ON cd.consumption_id = alcad.consumption_id " +
            "JOIN cubic_meters_detail cubd ON cd.consumption_id = cubd.consumption_id ";

    @Query(
            nativeQuery = true,
            value = GET_CONSUMPTION_DETAILS + "WHERE c.apartment_name = :aptName AND c.bill_date = :billDate"
    )
    Optional<ConsumptionDetail> findByAptNameAndBillDate(String aptName, String billDate);

    @Query(
            nativeQuery = true,
            value = GET_CONSUMPTION_DETAILS + "WHERE c.bill_date = :billDate"
    )
    List<ConsumptionDetail> findAllByBillDate(String billDate);

    @Query(
            nativeQuery = true,
            value = GET_CONSUMPTION_DETAILS + "WHERE c.apartment_name = :aptName"
    )
    List<ConsumptionDetail> findAllByAptName(String aptName);


    @Query(
            nativeQuery = true,
            value = "SELECT apartment_name FROM consumption WHERE consumption_id = :consumptionId"
    )
    String getApartmentNameFromConsumptionId(int consumptionId);

    @Query(
            nativeQuery = true,
            value = "SELECT bill_date FROM consumption WHERE consumption_id = :consumptionId"
    )
    String getBillDateFromConsumptionId(int consumptionId);
}
