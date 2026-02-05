Feature: Car booking

  Scenario: Customer books an available car
    Given a customer exists
    And an available car exists
    When the customer books the car from "2024-06-01" to "2024-06-10"
    Then the booking is created
    And the car is no longer available
