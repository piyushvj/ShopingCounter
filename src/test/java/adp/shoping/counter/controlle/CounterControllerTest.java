package adp.shoping.counter.controlle;

import adp.shoping.counter.controller.CounterController;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CounterControllerTest {

    @InjectMocks
    private CounterController counterController;

    @Mock
    private InvoiceService invoiceService;

    @Test
    public void generateInvoiceTest_withSuccessResponse(){
        List<String> barcodeList = TestData.getBarcodes();
        Invoice invoice = TestData.getInvoice();
        when(invoiceService.generateInvoiceBill(barcodeList)).thenReturn(invoice);
        ResponseEntity<Invoice> responseEntity = counterController.generateInvoice(barcodeList);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void generateInvoiceTestWithNullBarcodeList_withEmptyBarcodes(){
        ResponseEntity<Invoice> responseEntity = counterController.generateInvoice(new ArrayList());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
