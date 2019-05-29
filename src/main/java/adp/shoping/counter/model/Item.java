package adp.shoping.counter.model;

/**
 * 1. An Item class, Used to create an item.
 *
 * 2. items are considered to be duplicate if item's barcode is same
 *
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

import java.io.Serializable;

public class Item implements Serializable {

    private final String barcode;
    private final String name;
    private final double price;
    private final String category;

    public Item(String barcode, String name, double price, String category){
        this.barcode = barcode;
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

    public String getBarcode() {  return barcode;  }

    public boolean equals(Object object) {
        if( object instanceof Item) {
            Item item = (Item) object;
            return item.getBarcode().equalsIgnoreCase(this.getBarcode());
        }else{
            return false;
        }
    }

    @Override
    public String toString (){
        return getBarcode()+" , "+ getName() +" , "+ getPrice() + " , " + getCategory();
    }
}