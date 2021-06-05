package com.isladellago.watercalculator.model.bill;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    Optional<Bill> findByBillDate(String billDate);

    void deleteByBillDate(String billDate);
}
