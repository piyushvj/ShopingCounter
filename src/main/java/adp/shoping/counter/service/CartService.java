package adp.shoping.counter.service;

import adp.shoping.counter.model.Item;
import adp.shoping.counter.repository.ShopDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A service layer class, to make customer cart of purchased item.
 *
 * @author Piyush Vijayvargiya
 * @version 1.0
 */

@Service
public class CartService {

    @Autowired
    private ShopDB shopDB;

    private Map<Item, Integer> cart;

    private static final Logger LOG = LoggerFactory.getLogger(CartService.class);

    public CartService() {
        cart = new HashMap<>();
    }

    public void addToCart(Item item) {
        if (cart.containsKey(item)) {
            int quantity = cart.get(item);
            cart.put(item, quantity + 1);
        } else {
            cart.put(item, 1);
        }
    }

    public Map<Item, Integer> getCustomerCart() {
        return cart;
    }

    public void createCart(List<String> barcodes) {
        for (String barcode : barcodes) {
            Item item = shopDB.searchItem(barcode);
            if(null !=item) {
                addToCart(item);
            }else {
                LOG.info("Item not found for barcode :" + barcode);
            }
        }
    }

    public int getCartSize() {
        return cart.size();
    }
}