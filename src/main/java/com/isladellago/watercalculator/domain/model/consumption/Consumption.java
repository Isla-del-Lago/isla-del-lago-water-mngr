package com.isladellago.watercalculator.domain.model.consumption;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Class responsible for map the consumption entity on isla_del_lago database.
 */
@Entity
@Table(name = "consumption")
@Data
@NoArgsConstructor
public final class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumption_id")
    private Integer consumptionId;

    @Column(name = "apartment_name")
    private String apartmentName;

    @Column(name = "bill_date")
    private String billDate;

    @Column(name = "meter_value")
    private String meterValue;

    @Column(name = "value_photo_url")
    private String valuePhotoUrl;

    @Column(name = "previous_consumption_value")
    private String previousConsumptionValue;

    @Column(name = "creation_date")
    private Date creationDate = new Date();
}
