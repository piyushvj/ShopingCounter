package adp.shoping.counter.utility;

/**
 * An enum which keeps the category mapped with corresponding tax.
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public enum ItemCategory {
    A(10), B(20), C(0);
    private double value;
    ItemCategory(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}
