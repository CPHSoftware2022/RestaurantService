package utils_testing;


import com.example.restaurantservice.entities.Item;
import com.example.restaurantservice.entities.Restaurant;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.repository.RestaurantRepository;
import com.example.restaurantservice.utils.GenerateData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class DataGeneratorTest {
    private final GenerateData service = new GenerateData();
    Restaurant restaurant = service.generateRestaurant();;
    Item item = service.generateItem(restaurant);;
    List<Restaurant> restaurantList = service.generateRestaurants(10);
    List<Item> itemList = service.generateItems(10, restaurant);

    @Test
    void bothCanBeGenerated() {
        assert restaurant != null && item != null;
    }
    @Test
    void allValuesArePresentInItem() {
        // Checking everything is present
        assert item.getName() != null;
        assert item.toString() != null;
        assertNotNull(item.getPrice());
        assert item.getRestaurant() != null;
    }

    @Test
    void allValuesArePresentInRestaurant() {
        // Checking if all values are present in restaurant
        assert restaurant.getName() == null;
        assert restaurant.getPhone()!=null;
        assertNotNull(restaurant.isPartner());

    }

    @Test
    void rightNumberRestaurentsGetsGenerated() {
        assertEquals(restaurantList.size(),10);
    }

    @Test
    void rightNumberItemsGetsGenerated() {
        assertEquals(itemList.size(),10);
    }

    @Test
    void allItemsGetsGeneratedWithRestaurent() {
        // Checking if all values are present in restaurant
        for (Item value : itemList) {
            assertNotNull(value.getRestaurant());
        }
    }
}