package adp.shoping.counter.service;

import adp.shoping.counter.model.*;
import adp.shoping.counter.repository.ShopDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This a main service class, which keeps the logic of calculating total price, tax etc.
 *
 * @author Piyush Vijayvargiya
 * @version 1.0
 */

@Service
public class InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private ShopDB shopDB;

    public double getApplicableTaxAmount(double itemPrice, String itemCategory, Integer quantity) {
        return quantity * ((itemPrice * shopDB.getTax(itemCategory)) / 100);
    }

    public Invoice generateInvoiceBill(List<String> barcodes) {
        Invoice invoice = new Invoice();
        cartService.createCart(barcodes);
        Map<Item, Integer> itemCount = cartService.getCustomerCart();
        Set<Item> itemSet = itemCount.keySet();
        InvoiceRow invoiceRow;
        int serialNumber = 0;
        for (Item item : itemSet) {
            invoiceRow = new InvoiceRow();
            invoiceRow.setSerialNumber(++serialNumber);
            invoice.setTotalItem(invoice.getTotalItem()+itemCount.get(item));
            double taxAmout = getApplicableTaxAmount(item.getPrice(), item.getCategory(), itemCount.get(item));
            invoiceRow.setItemName(item.getName());
            invoiceRow.setItemPrice(item.getPrice());
            invoiceRow.setServiceTax(taxAmout);
            invoiceRow.setItemQuantity(itemCount.get(item));
            invoice.getInvoiceRowList().add(invoiceRow);
            if (itemCount.get(item) > 1) {
                invoice.setTotalPrice(invoice.getTotalPrice() + item.getPrice() * itemCount.get(item));
            } else {
                invoice.setTotalPrice(invoice.getTotalPrice() + item.getPrice());
            }
            invoice.setTotalTax(invoice.getTotalTax() + taxAmout);
        }
        itemCount.clear();
        invoice.setTotalBillAmount(invoice.getTotalPrice() + invoice.getTotalTax());
        return invoice;
    }
}
