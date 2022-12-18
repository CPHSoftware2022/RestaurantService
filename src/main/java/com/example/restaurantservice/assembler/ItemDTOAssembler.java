package com.example.restaurantservice.assembler;

import com.example.restaurantservice.controllers.RestaurantController;
import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.entities.Item;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
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
            itemDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class).getRestaurant(entity.getId())).withSelfRel());
            return itemDTO;
        }

    @Override
    public CollectionModel<ItemDTO> toCollectionModel(Iterable<? extends Item> entities)
    {
        CollectionModel<ItemDTO> itemDTOS = super.toCollectionModel(entities);
        itemDTOS.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class).getRestaurants()).withSelfRel());
        return itemDTOS;
    }

}
