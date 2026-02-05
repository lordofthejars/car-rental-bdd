package org.acme;

import io.quarkus.runtime.Startup;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class DataInitializer {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CarRepository carRepository;

    @Transactional
    @Startup
    void init() {

        Car car = new Car();
        car.id = 1L;
        car.model = "Toyota Corolla";
        car.available = true;
        carRepository.persist(car);

        Customer customer = new Customer();
        customer.id = 1L;
        customer.name = "Alice";
        customerRepository.persist(customer);
    }
}
