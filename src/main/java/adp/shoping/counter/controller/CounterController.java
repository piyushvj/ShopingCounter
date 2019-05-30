package adp.shoping.counter.controller;

import adp.shoping.counter.model.Invoice;
import adp.shoping.counter.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Main controller class for the whole application.
 * And output
 * @version 1.0
 * @author Piyush Vijayvargiya
 */

@RestController
@RequestMapping("/counter")
public class CounterController {

    @Autowired
    private InvoiceService invoiceService;

    private static final Logger LOG= LoggerFactory.getLogger(InvoiceService.class);

    @RequestMapping(value = "/generateInvoice", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Invoice> generateInvoice(@RequestBody (required = true) List<String> barcodes){

        Invoice invoice;
        HttpStatus status;
        if(barcodes.size()>0) {
            invoice = invoiceService.generateInvoiceBill(barcodes);
            status = HttpStatus.OK;
            LOG.info("Transaction Successful");
        }else {
            invoice = new Invoice();
            status = HttpStatus.BAD_REQUEST;
            LOG.info("No Barcode entered");
        }
        return new ResponseEntity<>(invoice, status);
    }
}