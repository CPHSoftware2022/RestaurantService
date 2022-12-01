package com.example.restaurantservice.assembler;

import com.example.restaurantservice.controllers.FoodToGoController;
import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.entities.Restaurant;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RestaurantDTOAssembler extends RepresentationModelAssemblerSupport <Restaurant,RestaurantDTO>{

    public RestaurantDTOAssembler() {
        super(FoodToGoController.class, RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO toModel(Restaurant entity) {
        RestaurantDTO restaurantDTO = instantiateModel(entity);
        restaurantDTO.setId(entity.getId());
        restaurantDTO.setName(entity.getName());
        restaurantDTO.setPhone(entity.getPhone());
        restaurantDTO.setPartner(entity.isPartner());
        restaurantDTO.add(linkTo(methodOn(FoodToGoController.class).getRestaurant(entity.getId())).withSelfRel());
        return restaurantDTO;
    }

    @Override
    public CollectionModel<RestaurantDTO> toCollectionModel(Iterable<? extends Restaurant> entities)
    {
        CollectionModel<RestaurantDTO> restaurantDTOS = super.toCollectionModel(entities);
        restaurantDTOS.add(linkTo(methodOn(FoodToGoController.class).getRestaurants()).withSelfRel());
        return restaurantDTOS;
    }
}
