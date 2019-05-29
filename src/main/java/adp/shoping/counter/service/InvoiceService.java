package adp.shoping.counter.service;

import adp.shoping.counter.exception.ItemNotFoundException;
import adp.shoping.counter.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This a main service class, which keeps the logic of calculating total price, tax etc.
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

@Service
public class InvoiceService {

    private static final Logger LOG= LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private Shop shop;

    public double getApplicableTaxAmount(double itemPrice, String itemCategory, Integer quantity){

        if(!StringUtils.isEmpty(itemPrice) && !StringUtils.isEmpty(itemCategory) && !StringUtils.isEmpty(quantity)) {
            return calculateTax(itemPrice, shop.getTax(itemCategory), quantity);
        }
        return 0;
    }

    private double calculateTax(double itemPrice, double percentage, Integer quantity){

        return quantity*((itemPrice * percentage)/100);
    }

    public Invoice generateInvoiceBill(List<String> barcodes){
        Invoice invoice = new Invoice();
        if(!StringUtils.isEmpty(barcodes)) {
            invoice.setTotalItem(barcodes.size());
            cartService.createCart(barcodes);
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
            itemCount.clear();
            invoice.setTotalBillAmount(invoice.getTotalPrice()+invoice.getTotalTax());
        } else {
            LOG.error("Item Not Found");
            throw new ItemNotFoundException();
        }
        return invoice;
    }
}
