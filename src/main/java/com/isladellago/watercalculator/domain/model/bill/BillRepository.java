package com.isladellago.watercalculator.domain.model.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    void deleteByBillDate(String billDate);

    Optional<Bill> findByBillDate(String billDate);
}
