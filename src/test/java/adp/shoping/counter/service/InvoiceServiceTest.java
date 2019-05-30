package adp.shoping.counter.service;

import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.repository.ShopDB;
import adp.shoping.counter.util.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private CartService cartService;

    @Mock
    private ShopDB shop;

    @Test
    public void getApplicableTaxAmountTest() throws Exception{
        when(shop.getTax("A")).thenReturn(10);
        double taxAmount = invoiceService.getApplicableTaxAmount(1000, "A", 2);
        System.out.println(taxAmount);
        Assert.assertEquals(200, taxAmount, 0);
    }

    @Test
    public void getApplicableTaxAmountTestWhenCategoryIsEmpty() throws Exception{
        double taxAmount = invoiceService.getApplicableTaxAmount(1000, "", 2);
        System.out.println(taxAmount);
        Assert.assertEquals(0, taxAmount, 0);
    }

    @Test
    public void generateInvoiceBillTest() throws Exception{
        List<String> barcodes = TestData.getBarcodes();
        when(cartService.getCustomerCart()).thenReturn(TestData.getItemCount());
        when(shop.getTax("A")).thenReturn(10);
        Invoice invoice = invoiceService.generateInvoiceBill(barcodes);
        Assert.assertNotNull(invoice);
    }
}
