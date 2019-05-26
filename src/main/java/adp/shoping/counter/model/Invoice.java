package adp.shoping.counter.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Main class for final generated invoice.
 * it keeps the structure of whole invoice bill.
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

public class Invoice {
    private List<InvoiceRow> invoiceRowList;
    private int totalItem;
    private double totalPrice;
    private double totalTax;
    private double totalBillAmount;

    public Invoice(){
        this.invoiceRowList = new LinkedList<>();
    }

    public List<InvoiceRow> getInvoiceRowList() {
        return invoiceRowList;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

}