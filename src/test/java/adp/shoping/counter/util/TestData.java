package adp.shoping.counter.util;

import adp.shoping.counter.model.BarcodeWrapper;
import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.model.InvoiceRow;
import adp.shoping.counter.model.Item;
import adp.shoping.counter.service.InvoiceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    public static Invoice getInvoice(){
        Invoice invoice = new Invoice();
        List<InvoiceRow> invoiceRowList = invoice.getInvoiceRowList();
        InvoiceRow invoiceRow = createInvoiceRow();
        invoiceRowList.add(invoiceRow);
        invoice.setTotalTax(120);
        invoice.setTotalPrice(15000);
        invoice.setTotalItem(2);
        invoice.setTotalBillAmount(150510);
        return invoice;
    }

    public static BarcodeWrapper getBarcodeWrapper(){
        BarcodeWrapper barcodeWrapper = new BarcodeWrapper();
        List<String> barcodes = new ArrayList<>();
        barcodes.add("A001");
        barcodes.add("B001");
        barcodes.add("C001");
        barcodes.add("A001");
        barcodeWrapper.setBarcods(barcodes);
        return barcodeWrapper;
    }

    public static Item getItem(String name, double price, String category){
        return new Item(name, price, category);
    }

    public static Map<Item, Integer> getItemCount() {
        Map<Item, Integer> itemCount = new HashMap<>();
        itemCount.put(getItem("Jeans", 3000, "A"),2);
        itemCount.put(getItem("Potato", 20, "B"),1);
        itemCount.put(getItem("Guitar", 15000, "C"),3);
        return itemCount;
    }


    private static InvoiceRow createInvoiceRow(){
        InvoiceRow invoiceRow = new InvoiceRow();
        invoiceRow.setSerialNumber(1);
        invoiceRow.setItemPrice(1000);
        invoiceRow.setItemName("TestData");
        invoiceRow.setItemQuantity(2);
        invoiceRow.setTaxAmount(100);
        return invoiceRow;
    }
}
