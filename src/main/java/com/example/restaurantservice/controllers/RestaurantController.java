package com.example.restaurantservice.controllers;

import com.example.restaurantservice.assembler.ItemDTOAssembler;
import com.example.restaurantservice.assembler.RestaurantDTOAssembler;
import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import com.example.restaurantservice.model.EventModel;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.RestaurantRepository;
import com.example.restaurantservice.services.ProducerService;
import com.example.restaurantservice.utils.GenerateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class RestaurantController {
    @Autowired
    private ProducerService service;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private RestaurantDTOAssembler restaurantDTOAssembler;

    @Autowired
    private ItemDTOAssembler itemDTOAssembler;

    public RestaurantController() {
    }

    public RestaurantController(RestaurantRepository repository, ItemRepository itemRepository) {
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
        List<Restaurant> restaurantList= generateData.generateRestaurants(100);
        for (Restaurant restaurant : restaurantList) {
            restaurantRepository.save(restaurant);
            List<Item> itemList = generateData.generateItems(10, restaurant);
            //This is so much faster than iterating through the list and saving each item
            itemRepository.saveAll(itemList);
        }

        return restaurantList;
    }

    @GetMapping("restaurants")
    @Cacheable(value = "restaurants")
    public ResponseEntity<CollectionModel<RestaurantDTO>> getRestaurants() {
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
        ResponseEntity<CollectionModel<RestaurantDTO>> responseEntity= new ResponseEntity<>(restaurantDTOAssembler.toCollectionModel(restaurantList), HttpStatus.OK);
        EventModel eventModel = new EventModel("GET", responseEntity.getStatusCode(), "RestaurantDTOAssembler{size="+restaurantList.size()+"}");
        service.sendMessage(eventModel.toString());
        return responseEntity;
    }

    //Problem with Response entity format makes it not cacheable but thought I would keep this in anyway to show my attempt at caching the response
    @GetMapping("restaurants2")
    @Cacheable(value = "restaurants")
    public List<Restaurant> cachedGetAll() {
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
        return restaurantList;
    }

    @GetMapping(value = "restaurantItems/{id}")
    ResponseEntity<CollectionModel<ItemDTO>> getRestaurantItems(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        List<Item>items= itemRepository.findAllByRestaurantId(restaurant.getId());
        ResponseEntity<CollectionModel<ItemDTO>> responseEntity= new ResponseEntity<>(itemDTOAssembler.toCollectionModel(items), HttpStatus.OK);
        EventModel eventModel = new EventModel("GET", responseEntity.getStatusCode(), "Restaurant Item:"+ "{size="+items.size()+"}");
        service.sendMessage(eventModel.toString());
        return responseEntity;
    }

    //get restaurant by id
    @GetMapping(value = "restaurant/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        ResponseEntity<RestaurantDTO> responseEntity = new ResponseEntity<>(restaurantDTOAssembler.toModel(restaurant), HttpStatus.OK);
        EventModel eventModel = new EventModel("GET", responseEntity.getStatusCode(), "ItemDTOAssembler{size="+restaurant+"}");
        service.sendMessage(eventModel.toString());
        return responseEntity;
    }

    @GetMapping("item/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        Item item = itemRepository.findById(id).get();
        ResponseEntity<ItemDTO> responseEntity= new ResponseEntity<>(itemDTOAssembler.toModel(item) , HttpStatus.OK);
        EventModel eventModel = new EventModel("GET", responseEntity.getStatusCode(), "Item{size="+item);
        service.sendMessage(eventModel.toString());
        return responseEntity;
    }
/*

    @GetMapping("items")
    public ResponseEntity<CollectionModel<ItemDTO>> getItems() {
        List<Item> itemList = (List<Item>) itemRepository.findAll();
        ResponseEntity responseEntity= new ResponseEntity<>(itemDTOAssembler.toCollectionModel(itemList), HttpStatus.OK);
        EventModel eventModel = new EventModel("GET", responseEntity.getStatusCode(), "ItemDTOAssembler{size="+itemList.size()+"}");
        service.sendMessage(eventModel.toString());
        return responseEntity;
    }

    @PostMapping("item")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
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
    }*/

}
