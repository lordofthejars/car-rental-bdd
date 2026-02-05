package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Car {

    @Id
    public Long id;
    public String model;
    public boolean available;

}
