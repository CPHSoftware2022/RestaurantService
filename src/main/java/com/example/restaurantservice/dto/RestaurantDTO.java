package com.example.restaurantservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestaurantDTO extends RepresentationModel<RestaurantDTO> implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private boolean isPartner;

}
