package com.isladellago.watercalculator.model.bill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, String> {
}
