package com.example.restaurantservice.controllers;

import com.example.restaurantservice.assembler.ItemDTOAssembler;
import com.example.restaurantservice.assembler.RestaurantDTOAssembler;
import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.RestaurantRepository;
import com.example.restaurantservice.utils.GenerateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FoodToGoController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private RestaurantDTOAssembler restaurantDTOAssembler;

    @Autowired
    private ItemDTOAssembler itemDTOAssembler;

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
    public ResponseEntity<CollectionModel<RestaurantDTO>> getRestaurants() {
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
        return new ResponseEntity<>(restaurantDTOAssembler.toCollectionModel(restaurantList), HttpStatus.OK);
    }

    @GetMapping(value = "restaurantItems/{id}")
    List<Item> getRestaurantItems(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        return itemRepository.findAllByRestaurantId(restaurant.getId());
    }

    //get restaurant by id
    @GetMapping(value = "restaurant/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        System.out.println(restaurantDTOAssembler.toModel(restaurant));
        return new ResponseEntity<>(restaurantDTOAssembler.toModel(restaurant), HttpStatus.OK);
    }

    @GetMapping("items")
    public ResponseEntity<CollectionModel<ItemDTO>> getItems() {
        List<Item> itemList = (List<Item>) itemRepository.findAll();
        return new ResponseEntity<>(itemDTOAssembler.toCollectionModel(itemList), HttpStatus.OK);
    }

    @PostMapping("item")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping("item/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        Item item = itemRepository.findById(id).get();
        return new ResponseEntity<>(itemDTOAssembler.toModel(item), HttpStatus.OK);
    }

    @PostMapping("restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
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
