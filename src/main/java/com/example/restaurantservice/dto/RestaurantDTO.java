package com.example.restaurantservice.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class RestaurantDTO extends RepresentationModel<RestaurantDTO> {
    private Long id;
    private String name;
    private String phone;
    private boolean isPartner;

}
