package adp.shoping.counter.service;

import adp.shoping.counter.model.Item;
import adp.shoping.counter.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A service layer class, to make customer cart of purchased item.
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

@Service
public class CartService {

    @Autowired
    private Shop shop;

    public CartService() {
        cart = new LinkedHashMap<>();
    }

    private Map<Item, Integer> cart;

    public void addToCart(Item item) {
        if(cart.containsKey(item)) {
            int quantity = cart.get(item);
            cart.put(item, quantity+1);
        }else{
            cart.put(item, 1);
        }
    }

    public Map<Item, Integer> getCustomerCart(){
        return cart;
    }

    public void createCart(List<String> barcodes){
        for(String barcode : barcodes) {
            Item item = shop.searchItem(barcode);
            addToCart(item);
        }
    }
}
