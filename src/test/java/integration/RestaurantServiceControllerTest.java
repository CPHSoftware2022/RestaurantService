package integration;
import com.example.restaurantservice.controllers.FoodToGoController;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    private RestaurantRepository restaurantRepository = Mockito.mock(RestaurantRepository.class);

    @Autowired
    private ItemRepository itemRepository = Mockito.mock(ItemRepository.class);

    // write test cases here
    FoodToGoController foodToGoController = new FoodToGoController();

    @Test
    public void test() {
        assertEquals(foodToGoController.test(), "Hello");
    }

   /* @Test
    public void getAllRestaurants() {
        FoodToGoController foodToGoController = new FoodToGoController(restaurantRepository, itemRepository);
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Name","6060606060",true));
        when(restaurantRepository.findAll()).thenReturn(restaurantList);
       // assertEquals(foodToGoController.getRestaurants().size(), 1);
        assertEquals(foodToGoController.getRestaurants().getStatusCode(), 200);
    }



    @Test
    public void getAllItems() {
        FoodToGoController foodToGoController = new FoodToGoController(restaurantRepository, itemRepository);
        List<Item> items = new ArrayList<>();
        items.add(new Item(1l,"Burger",22.2));
        when(itemRepository.findAll()).thenReturn(items);
        assertEquals(foodToGoController.getItems().getStatusCode(), 200);
        assertEquals(foodToGoController.getItems().hasBody(), true);

    }*/


}