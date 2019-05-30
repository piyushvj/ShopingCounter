package adp.shoping.counter.repository;
import adp.shoping.counter.exception.InvalidFilePathException;
import adp.shoping.counter.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.InvalidPathException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import sun.dc.path.PathException;

/**
 * The class helps to settle up the shop with few items.
 * And output
 *
 * @author Piyush Vijayvargiya
 * @version 1.0
 */

public class ShopDB {

    private static final Logger LOG = LoggerFactory.getLogger(ShopDB.class);

    private List<Item> itemList;

    private Map<String, Item> items;

    private Map<String, Integer> categoryMap;


    public ShopDB(final String categoryPath, final String itemsPath) {
        this.items = new HashMap<>();
        this.shopReady(categoryPath, itemsPath);
    }

    public void shopReady(String categoryPath, String itemsPath) {
        this.loadCategory(categoryPath);
        this.loadItems(itemsPath);
        this.setItemsInShop(itemList);
    }

    public void loadCategory(String categoryPath) {
        if(null==categoryPath){
            LOG.info("category part not give");
            throw new InvalidFilePathException("categoryPath");
        }
        Gson gson = new Gson();
        try (Reader reader = new FileReader(ResourceUtils.getFile("classpath:"+categoryPath))) {
            Type mapType = new TypeToken<Map<String, Integer>>(){}.getType();
            categoryMap = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            LOG.error("Error while reading json" + e);
        }
    }

    public void setItemsInShop(List<Item> itemList) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            items.put(item.getBarcode(), item);
        }
    }

    public Item searchItem(String barcode) {
        return this.items.get(barcode);
    }

    public Integer getTax(String category) {
        return categoryMap.get(category);
    }

    public void loadItems(String path) {
        if(null==path){
            LOG.info("Item path not give");
            throw new InvalidFilePathException("Item path not given");
        }
        Gson gson = new Gson();
        try (Reader reader = new FileReader(ResourceUtils.getFile("classpath:"+path))) {
            Type listType = new TypeToken<List<Item>>() {
            }.getType();
            itemList = gson.fromJson(reader, listType);
        } catch (IOException e) {
            LOG.error("Error while reading json" + e);
        }
    }

    public Map<String, Integer> getCategoryMap(){
        return categoryMap;
    }
}