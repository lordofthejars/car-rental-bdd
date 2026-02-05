package org.acme;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

public class CarBookingSteps {

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

    @And("an available car exists")
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
                        .header("Content-Type", "application/json")
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

    @And("the car is no longer available")
    public void carNotAvailable() {
        car =
                given()
                        .when()
                        .get("/cars/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Car.class);

        assertThat(car.available).isFalse();
    }

    @After
    public void cleanDatabase() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestDelete = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/bookings"))
                .DELETE()
                .build();

        client.send(requestDelete, HttpResponse.BodyHandlers.discarding());

        HttpRequest requestUpdate = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/cars/1"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        client.send(requestUpdate, HttpResponse.BodyHandlers.discarding());
    }

}
