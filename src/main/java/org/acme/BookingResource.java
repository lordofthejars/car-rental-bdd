package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@Path("/bookings")
public class BookingResource {

    @Inject
    CarRepository carRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    BookingRepository bookingRepository;

    @DELETE
    @Transactional
    public void delete() {
        this.bookingRepository.deleteAll();
    }

    @POST
    @Transactional
    public Booking book(BookingRequest bookingRequest) {

        Car car = carRepository.findById(bookingRequest.carId);
        Customer customer = customerRepository.findById(bookingRequest.customerId);

        if (car == null || customer == null || !car.available) {
            throw new BadRequestException("Invalid booking request");
        }

        Booking booking = new Booking();
        booking.car = car;
        booking.customer = customer;
        booking.startDate = bookingRequest.startDate;
        booking.endDate = bookingRequest.endDate;

        bookingRepository.persist(booking);
        car.available = false;

        return booking;

    }

}
