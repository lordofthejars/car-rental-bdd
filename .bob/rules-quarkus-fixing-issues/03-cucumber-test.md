



## Skill: Implements a Cucumber Test with Java

### Purpose
Enable Bob to scaffold Cucumber Tests.

### Overview
Create Cucumber tests following the given `.feature` file in Gherkin format. Use Cucumber-Java Standalone. *Don't Use* the Quarkus extension.

### Core Rules
1. Always depend on `io.cucumber:cucumber-java` and its JUnit integration `io.cucumber:cucumber-junit-platform-engine`.
2. Steps class must be finished with `Steps` word, for example, `CarBookingSteps`.
3. Use the `@io.cucumber.java.en.Given`, `@io.cucumber.java.en.When`, `@io.cucumber.java.en.Then`, and `@io.cucumber.java.en.And` annotations matching the Gherkin definition.
4. Quoted strings in Gherkin text means a parameter in the test.
5. You can use Rest-Assured and AssertJ for implementing the use cases.
6. If using Rest-Assured, the default URI is the correct to connect.
7. Annotate a method with `io.cucumber.java.After` to run any logic after the execution of a scenario

### Example Resource

With the following scenario:

```gherkin
Scenario: Customer books an available car
    Given a customer exists
    And an available car exists
    When the customer books the car from "2024-06-01" to "2024-06-10"
    Then the booking is created
```

The test should be the following one having dates in `When` as parameters because they are quoted:

```java
public class CarBookingSteps {

    private Customer customer;
    private Car car;
    private Booking booking;

    @Given("a customer exists")
    public void customerExists() {

    }

    @And("an available car exists")
    public void carExists() {

    }

    @When("the customer books the car from {string} to {string}")
    public void bookCar(String start, String end) {
        
    }

    @Then("the booking is created")
    public void bookingCreated() {

    }

}

```

### Notes
- Always verify that the parameters in the Gherkin feature file. If strings are quoted then it is a paramter. 
- Always check if there is already an existing entity or repository before creating it.

