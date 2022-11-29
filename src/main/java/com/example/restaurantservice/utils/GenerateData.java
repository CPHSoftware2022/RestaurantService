package com.example.restaurantservice.utils;

import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class GenerateData {

    //generate data
    private Faker faker = new Faker();


    public Restaurant generateRestaurant(){
        String restaurantName = faker.company().name();
        String phoneNumber = faker.phoneNumber().cellPhone();
        boolean partner = faker.bool().bool();
        return new Restaurant( restaurantName, phoneNumber, partner);
    }

    public List<Restaurant> generateRestaurants(int amount){
        List<Restaurant> restaurantList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            restaurantList.add(generateRestaurant());
        }
        return restaurantList;
    }

    public Item generateItem(Restaurant restaurant){
        String itemName = faker.food().dish();
        double itemPrice = faker.number().randomDouble(2, 10, 100);
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(itemPrice);
        item.setRestaurant(restaurant);
        return item;
    }

    public List<Item> generateItems(int amount, Restaurant restaurant) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemList.add(generateItem(restaurant));
        }
        return itemList;
    }
}
