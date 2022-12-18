# RestaurantService
## This is the service that handles restaurants and items

# How to run
1. Setup mySQL on docker
2. Start the project


# What it contains
It generates restaurant and items.

It then has restfull level 3 apis for showing them in the app.  

Testing API's
Generates restaurants and items: http://localhost:8100/generateRestaurants

Normal API's

- http://localhost:8100/restaurants - Gets all restaurants

- http://localhost:8100/restaurant/{id} - Gets restaurant by ID

- http://localhost:8100/item/{id} - Gets Item by ID

- http://localhost:8100/restaurantItems/{id} - Gets all items from a specific restaurant


It is connected to a kafka consumer that handles logging og interactions with the API

It contains 15 tests of different varieties.
- unit
- integration
- acceptance
