package org.acme;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.time.LocalDate;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BookingSteps {

    private Customer customer;
    private Car car;
    private Booking booking;

    @Given("a customer exists")
    public void customerExists() {
        customer =
                given()
                        .when()
                        .get("/customers/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Customer.class);
    }

    @Given("an available car exists")
    public void carExists() {
        car =
                given()
                        .when()
                        .get("/cars/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Car.class);

        assertThat(car.available).isTrue();
    }

    @When("the customer books the car from {string} to {string}")
    public void bookCar(String start, String end) {

        booking =
                given()
                        .body(new BookingRequest() {{
                            carId = car.id;
                            customerId = customer.id;
                            startDate = LocalDate.parse(start);
                            endDate = LocalDate.parse(end);
                        }})
                        .when()
                        .post("/bookings")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Booking.class);
    }

    @Then("the booking is created")
    public void bookingCreated() {
        assertThat(booking.id).isNotNull();
        assertThat(car.id).isEqualTo(booking.car.id);
        assertThat(customer.id).isEqualTo(booking.customer.id);
    }

}
