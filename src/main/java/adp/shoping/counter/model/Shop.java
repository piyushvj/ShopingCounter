package adp.shoping.counter.model;

import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class helps to settle up the shop with few items,
 * since there can be only one shop thus designed as singleton.
 * And output
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public class Shop {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG= LoggerFactory.getLogger(Shop.class);
    private Map<String, Item> items;

    private Shop(){
        this.items = new TreeMap<>();
    }

    private static class ShopLazyLoad {
        private static final Shop INSTANCE = new Shop();
    }

    public static Shop getInstance(){
        return ShopLazyLoad.INSTANCE;
    }

    protected Object readResolve(){
        return getInstance();
    }

    public void addItem(String barcode, Item item){
        if(null != barcode && null != item) {
             this.items.put(barcode, item);
        }else {
            LOG.info("barcode & Item not provided");
        }
    }

    public void removeItem(String barcode){
        if( null != barcode && StringUtils.isEmpty(barcode)){
            if(this.items.containsKey(barcode)){
                items.remove(barcode);
            }else {
                LOG.info("Item for the barcode given barcode doesn't exist");
            }
        } else {
            LOG.error("barcode not provided");
        }
    }

    public Item searchItem(String barcode) {
        if(null != barcode && !StringUtils.isEmpty(barcode)) {
            return this.items.get(barcode);
        } else {
            LOG.info("barcode not provided");
            return null;
        }
    }
}