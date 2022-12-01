Feature: restaurants api
    Scenario: get one restaurant
    When I send a GET request to "/restaurant/1"
    Then the response status code should be 200
        And the client receives a Customer object

    Scenario: get all restaurants
        When I send new GET request to "/restaurants"
        Then the response status code should be 200
        And the client receives a list of Customer objects