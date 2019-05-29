package adp.shoping.counter.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class helps to settle up the shop with few items.
 * And output
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public class Shop {

    private static final Logger LOG= LoggerFactory.getLogger(Shop.class);

    private List<Item> itemList;

    private Map<String, Item> items;

    private Map<String, Integer> categoryMap;

    public Shop(String categoryPath, String itemsPath){
        this.items = new HashMap<>();
        this.shopReady(categoryPath, itemsPath);
    }

    public void shopReady(String categoryPath, String itemsPath) {
        this.loadCategory(categoryPath);
        this.loadItems(itemsPath);
        this.setItemsInShop(itemList);
    }

    public void loadCategory(String categoryPath){
        Gson gson = new Gson();
        try (Reader reader = new FileReader(categoryPath)) {
            Type mapType = new TypeToken<Map<String, Integer>>(){}.getType();
            categoryMap  = gson.fromJson(reader, mapType);
        }catch (IOException e) {
            LOG.error("Error while reading json" + e);
        }
    }

    public void setItemsInShop(List<Item> itemList){
        for(int i = 0; i<itemList.size(); i++){
            Item item = itemList.get(i);
            items.put(item.getBarcode(), item);
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

    public Integer getTax(String category) {
        return categoryMap.get(category);
    }

    public void loadItems(String path){
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            Type listType = new TypeToken<List<Item>>(){}.getType();
            itemList= gson.fromJson(reader, listType);
        }catch (IOException e) {
            LOG.error("Error while reading json" + e);
        }
    }
}