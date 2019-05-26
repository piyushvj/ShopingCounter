package adp.shoping.counter.model;

public class InvoiceRow {
    private int serialNumber = 0;
    private String itemName;
    private double itemPrice;
    private double taxAmount;
    private int itemQuantity = 0;

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setServiceTax(double taxAmount) {
        this.taxAmount = taxAmount;
    }
}
