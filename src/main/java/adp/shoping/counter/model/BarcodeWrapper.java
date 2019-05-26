package adp.shoping.counter.model;

import java.util.List;

/**
 * A Barcode wrapper class, which takes the barcode of all purchased items. *
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public class BarcodeWrapper {

    private List<String> barcods;

    public List<String> getBarcods() {
        return barcods;
    }

    public void setBarcods(List<String> barcods) {
        this.barcods = barcods;
    }

}
