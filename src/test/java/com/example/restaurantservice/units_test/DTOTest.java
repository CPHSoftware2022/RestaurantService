package com.example.restaurantservice.units_test;

import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.dto.RestaurantDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DTOTest {

    //Testing data is not being inserted on creation

    @Test
    public void RestaurantDTOTest() {
        // Make RestaurantDTO
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        assert(restaurantDTO.getId() == null);
        assert(restaurantDTO.getName() == null);
        assert(restaurantDTO.getPhone() == null);
        assert(restaurantDTO.isPartner() == false);
    }

    @Test
    public void ItemDTOTest(){
        // Make ItemDTO
        ItemDTO itemDTO = new ItemDTO();
        assert(itemDTO.getId() == null);
        assert(itemDTO.getName() == null);
        assert(itemDTO.getPrice() == 0.0);
        assert(itemDTO.getRestaurant() == null);

    }
}
