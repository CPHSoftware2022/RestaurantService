package com.example.restaurantservice.assembler;

import com.example.restaurantservice.controllers.FoodToGoController;
import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@Component
public class ItemDTOAssembler  extends RepresentationModelAssemblerSupport<Item, ItemDTO> {

        public ItemDTOAssembler() {
            super(Item.class, ItemDTO.class);
        }

        @Override
        public ItemDTO toModel(Item entity) {
            ItemDTO itemDTO = instantiateModel(entity);
            itemDTO.setId(entity.getId());
            itemDTO.setName(entity.getName());
            itemDTO.setPrice(entity.getPrice());
            itemDTO.setRestaurant(entity.getRestaurant());
            itemDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FoodToGoController.class).getRestaurant(entity.getId())).withSelfRel());
            return itemDTO;
        }

    @Override
    public CollectionModel<ItemDTO> toCollectionModel(Iterable<? extends Item> entities)
    {
        CollectionModel<ItemDTO> itemDTOS = super.toCollectionModel(entities);
        itemDTOS.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FoodToGoController.class).getRestaurants()).withSelfRel());
        return itemDTOS;
    }

}
