package org.acme;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@Path("/cars")
public class CarResource {

    @Inject
    CarRepository carRepository;

    @PUT
    @Transactional
    @Path("/{id}")
    public Car free(@RestPath Long id) {
        Car car = this.carRepository.findById(id);
        car.available = true;

        return car;
    }

    @GET
    @Path("/{id}")
    public Car findCarById(@RestPath Long id) {
        return carRepository.findById(id);
    }

}
