package adp.shoping.counter.controlle;

import adp.shoping.counter.controller.CounterController;
import adp.shoping.counter.model.BarcodeWrapper;
import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.service.InvoiceService;
import adp.shoping.counter.util.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CounterControllerTest {

    @InjectMocks
    private CounterController counterController;

    @Mock
    private InvoiceService invoiceService;

    @Test
    public void generateInvoiceTest(){
        BarcodeWrapper barcodeWrapper = TestData.getBarcodeWrapper();
        Invoice invoice = TestData.getInvoice();
        when(invoiceService.generateInvoiceBill(barcodeWrapper)).thenReturn(invoice);
        ResponseEntity<Invoice> responseEntity = counterController.generateInvoice(barcodeWrapper);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void generateInvoiceTest_WithNullBarcoderWrapper(){
        ResponseEntity<Invoice> responseEntity = counterController.generateInvoice(null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
