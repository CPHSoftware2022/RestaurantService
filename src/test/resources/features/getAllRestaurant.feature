Feature: get all restaurants
    Scenario: get one restaurant
    When I send a GET request to "/restaurant/1"
    Then the response status code should be 200

  #Scenario: get all restaurants
  #  When I send a GET request to "/restaurants"
  #  Then the response status code should be 200