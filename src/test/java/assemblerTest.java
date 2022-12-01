import com.example.restaurantservice.assembler.ItemDTOAssembler;
import com.example.restaurantservice.assembler.RestaurantDTOAssembler;
import com.example.restaurantservice.dto.ItemDTO;
import com.example.restaurantservice.dto.RestaurantDTO;
import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class assemblerTest {
    static List<Item> itemList = new ArrayList<>();
    static List<Restaurant> restaurantList = new ArrayList<>();

    @BeforeAll
    public static void init() {
        // This method is called before all tests
        itemList.add(new Item(1L, "item1", 1.0));
        restaurantList.add(new Restaurant(1L, "restaurant1", "address1", itemList));
    }

    @Test
    public void testItemAssemblerForSingleItem() {
        // This method is called before all tests
        ItemDTOAssembler assembler = new ItemDTOAssembler();
        ItemDTO response = assembler.toModel(itemList.get(0));
        assertEquals(response.getLinks().toString(), "</restaurant/1>;rel=\"self\"");
    }

    @Test
    public void testItemAssemblerForMultipleItems() {
        // This method is called before all tests
        ItemDTOAssembler assembler = new ItemDTOAssembler();
        CollectionModel<ItemDTO> response = assembler.toCollectionModel(itemList);
        itemList.add(new Item(2L, "item2", 2.0));

        assertEquals(response.getLinks().toString(), "</restaurants>;rel=\"self\"");
    }

    @Test
    public void testRestaurantAssemblerForMultipleItems() {
        RestaurantDTOAssembler assembler = new RestaurantDTOAssembler();
        CollectionModel<RestaurantDTO> response = assembler.toCollectionModel(restaurantList);
        assertEquals(response.getLinks().toString(), "</restaurants>;rel=\"self\"");
    }

    @Test
    public void testRestaurantAssemblerForSingleItem() {
        RestaurantDTOAssembler assembler = new RestaurantDTOAssembler();
        RestaurantDTO response = assembler.toModel(restaurantList.get(0));
        assertEquals(response.getLinks().toString(), "</restaurant/1>;rel=\"self\"");
    }

}
