package adp.shoping.counter.service;

import adp.shoping.counter.exception.ItemNotFoundException;
import adp.shoping.counter.model.BarcodeWrapper;
import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.model.Item;
import adp.shoping.counter.util.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private CartService cartService;

    @Test
    public void getApplicableTaxAmountTest() {
        double taxAmount = invoiceService.getApplicableTaxAmount(1000, "A", 2);
        System.out.println(taxAmount);
        Assert.assertEquals(200, taxAmount, 0);
    }

    @Test
    public void getApplicableTaxAmountTestWhenCategoryIsEmpty() {
        double taxAmount = invoiceService.getApplicableTaxAmount(1000, "", 2);
        System.out.println(taxAmount);
        Assert.assertEquals(0, taxAmount, 0);
    }

    @Test
    public void generateInvoiceBillTest() {
        BarcodeWrapper barcodeWrapper = TestData.getBarcodeWrapper();
        List<String> scannedBarcodes = TestData.getBarcodeWrapper().getBarcods();
        when(cartService.getCustomerCart()).thenReturn(TestData.getItemCount());
        Invoice invoice = invoiceService.generateInvoiceBill(barcodeWrapper);
        Assert.assertNotNull(invoice);
    }

    @Test(expected = ItemNotFoundException.class)
    public void generateInvoiceBillTestWhenNullBarcode(){
        invoiceService.generateInvoiceBill(null);
    }
}
