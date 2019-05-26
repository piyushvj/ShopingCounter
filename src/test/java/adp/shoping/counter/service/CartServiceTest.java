package adp.shoping.counter.service;

import adp.shoping.counter.model.BarcodeWrapper;
import adp.shoping.counter.model.Item;
import adp.shoping.counter.model.Shop;
import adp.shoping.counter.util.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    CartService cartService;

    @Mock
    Shop shop;

    @Test
    public void addToCartTestWithSingleType(){
        Item item = TestData.getItem("jeans", 3000, "A");
        cartService.addToCart(item);
    }

    @Test
    public void addToCartTestWithSameTypeTwice(){
        Map<Item, Integer> map = new HashMap<>();
        Item item1 = TestData.getItem("jeans", 3000, "A");
        map.put(item1, 2);
        ReflectionTestUtils.setField(cartService, "cart", map);
        cartService.addToCart(item1);
    }

    @Test
    public void getCustomerCartTest(){
        Map<Item, Integer> map = cartService.getCustomerCart();
        Assert.assertNotNull(map);
    }

    @Test
    public void createCartTest() {
        BarcodeWrapper barcodeWrapper = TestData.getBarcodeWrapper();
        cartService.createCart(barcodeWrapper.getBarcods());
    }
}
