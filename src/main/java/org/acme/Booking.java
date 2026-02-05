package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    public Car car;
    @ManyToOne
    public Customer customer;

    public LocalDate startDate;
    public LocalDate endDate;

}
