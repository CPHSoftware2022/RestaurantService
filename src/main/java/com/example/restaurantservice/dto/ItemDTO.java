package com.example.restaurantservice.dto;

import com.example.restaurantservice.entities.Restaurant;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ItemDTO extends RepresentationModel<ItemDTO> {
    private Long id;
    private String name;
    private double price;
    private Restaurant restaurant;
}
