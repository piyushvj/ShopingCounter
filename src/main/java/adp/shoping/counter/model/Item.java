package adp.shoping.counter.model;

/**
 * 1. An Item class, Used to create an item.
 *
 * 2. This is immutable class, because once item is created it can not be changed.
 * Also, its object is being used in some map as a key
 *
 * 3. items are considered to be duplicate in case of same item name
 *
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

import java.io.Serializable;

public class Item implements Serializable {

    private final String name;
    private final double price;
    private final String category;

    public Item(String name, double price, String category){
        this.price = price;
        this.category = category;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean equals(Object object) {
        if( object instanceof Item) {
            Item item = (Item) object;
            return item.getName().equalsIgnoreCase(this.name);
        }else{
            return false;
        }
    }

    @Override
    public String toString (){
        return getName() +" , "+ getPrice() + " , " + getCategory();

    }
}