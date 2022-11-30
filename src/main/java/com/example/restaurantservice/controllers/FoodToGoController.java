package com.example.restaurantservice.controllers;

// Spring api


import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.RestaurantRepository;
import com.example.restaurantservice.utils.GenerateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodToGoController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemRepository itemRepository;

    public FoodToGoController() {
    }

    public FoodToGoController(RestaurantRepository repository, ItemRepository itemRepository) {
        this.restaurantRepository = repository;
        this.itemRepository= itemRepository;
    }

    @GetMapping("/")
    public String test() {
        return "Hello";
    }

    @GetMapping("/generateRestaurants")
    List<Restaurant> restaurants() {
        GenerateData generateData = new GenerateData();
        List<Restaurant> restaurantList= generateData.generateRestaurants(1);
        for (int i = 0; i < restaurantList.size(); i++) {
            restaurantRepository.save(restaurantList.get(i));
            List<Item> itemList = generateData.generateItems(10, restaurantList.get(i));
            for (int j = 0; j < itemList.size(); j++) {
                itemRepository.save(itemList.get(j));
            }
        }

        return restaurantList;
    }

    @GetMapping("restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping(value = "restaurantItems/{id}")
    List<Item> getRestaurantItems(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        return itemRepository.findAllByRestaurantId(restaurant.getId());
    }

    //get restaurant by id
    @GetMapping(value = "restaurant/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantRepository.findById(id).get();
    }

    @GetMapping("items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("item")
    Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PostMapping("restaurant")
    Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("restaurant")
    public Restaurant editRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant restaurant1 = restaurantRepository.findById(restaurant.getId()).get();
        restaurant1.setName(restaurant.getName());
        restaurant1.setPhone(restaurant.getPhone());
        restaurant1.setPartner(restaurant.isPartner());
        return restaurantRepository.save(restaurant1);
    }

}
