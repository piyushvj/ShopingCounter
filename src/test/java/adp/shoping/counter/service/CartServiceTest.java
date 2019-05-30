package adp.shoping.counter.service;

import adp.shoping.counter.model.Item;
import adp.shoping.counter.repository.ShopDB;
import adp.shoping.counter.util.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    CartService cartService;

    @Mock
    ShopDB shop;

    @Before
    public void init(){
        Item item1 = TestData.getItem("A001","jeans", 3000, "A");
        Item item2 = TestData.getItem("A002","Shirt", 1500, "A");
        Item item3 = TestData.getItem("A003","Chinos", 1000, "A");
        cartService.addToCart(item1);
        cartService.addToCart(item2);
        cartService.addToCart(item3);
    }


    @Test
    public void addToCartTest_withOneMoreItem(){
        Item item4 = TestData.getItem("B001","Potato", 20, "B");
        cartService.addToCart(item4);
        Assert.assertEquals("check the size of list", 4, cartService.getCartSize());
    }

    @Test
    public void addToCartTestWithSameTypeTwice(){
        Item item4 = TestData.getItem("A003","Chinos", 1000, "A");
        cartService.addToCart(item4);
        Assert.assertEquals("Same item increments the count", new Integer(2), cartService.getCustomerCart().get(item4));
    }

    @Test
    public void getCustomerCartTest(){
        Map<Item, Integer> map = cartService.getCustomerCart();
        Assert.assertNotNull(map);
    }

    @Test
    public void createCartTest() {
        List<String> barcodes = TestData.getBarcodes();
        when(shop.searchItem(barcodes.get(0))).thenReturn(TestData.getItem("B001","Potato", 20, "A"));
        cartService.createCart(barcodes);
        Assert.assertEquals(4,cartService.getCartSize());
    }

    @Test
    public void createCartTest_whenNoItemInShop() {
        List<String> barcodes = TestData.getBarcodes();
        when(shop.searchItem(barcodes.get(0))).thenReturn(null);
        cartService.createCart(barcodes);
        Assert.assertEquals(3,cartService.getCartSize());
    }
}
