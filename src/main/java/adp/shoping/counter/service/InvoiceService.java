package adp.shoping.counter.service;

import adp.shoping.counter.exception.ItemNotFoundException;
import adp.shoping.counter.model.BarcodeWrapper;
import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.model.InvoiceRow;
import adp.shoping.counter.model.Item;
import adp.shoping.counter.utility.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * This a main service class, which keeps the logic of calculating total price, tax etc.
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

@Service
public class InvoiceService {

    @Autowired
    private CartService cartService;

    public double getApplicableTaxAmount(double itemPrice, String itemCategory, Integer quantity){
        if(!StringUtils.isEmpty(itemPrice) && !StringUtils.isEmpty(itemCategory) && !StringUtils.isEmpty(quantity)) {
            switch (itemCategory) {
                case "A":
                    return calculateTax(itemPrice, ItemCategory.A.getValue(), quantity);
                case "B":
                    return calculateTax(itemPrice, ItemCategory.B.getValue(), quantity);
                case "C":
                    return calculateTax(itemPrice, ItemCategory.C.getValue(), quantity);
                default:
                    return 0;
            }
        }else {
            return 0;
        }
    }

    private double calculateTax(double itemPrice, double percentage, Integer quantity){
        return quantity*((itemPrice * percentage)/100);
    }

    public Invoice generateInvoiceBill(BarcodeWrapper barcodes){
        Invoice invoice = new Invoice();
        if(null!=barcodes && !StringUtils.isEmpty(barcodes.getBarcods())) {
            invoice.setTotalItem(barcodes.getBarcods().size());
            cartService.createCart(barcodes.getBarcods());
            Map<Item, Integer> itemCount = cartService.getCustomerCart();
            Set<Item> itemSet = itemCount.keySet();
            InvoiceRow invoiceRow;
            int serialNumber = 0;
            for(Item item : itemSet){
                invoiceRow = new InvoiceRow();
                invoiceRow.setSerialNumber(++serialNumber);
                double taxAmout = getApplicableTaxAmount(item.getPrice(), item.getCategory(), itemCount.get(item));
                invoiceRow.setItemName(item.getName());
                invoiceRow.setItemPrice(item.getPrice());
                invoiceRow.setServiceTax(taxAmout);
                invoiceRow.setItemQuantity(itemCount.get(item));
                invoice.getInvoiceRowList().add(invoiceRow);
                if(itemCount.get(item)>1) {
                    invoice.setTotalPrice(invoice.getTotalPrice() + item.getPrice() * itemCount.get(item));
                }else{
                    invoice.setTotalPrice(invoice.getTotalPrice()+item.getPrice());
                }
                invoice.setTotalTax(invoice.getTotalTax() + taxAmout);
            }
            invoice.setTotalBillAmount(invoice.getTotalPrice()+invoice.getTotalTax());
        } else {
            throw new ItemNotFoundException();
        }
        return invoice;
    }
}
