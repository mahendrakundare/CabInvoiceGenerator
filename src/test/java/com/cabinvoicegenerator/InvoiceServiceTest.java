package com.cabinvoicegenerator;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceServiceTest {

    @Test
    public void givenDistanceAndTime_ReturnTotalFare_OfJourney_() {
        InvoiceService invoiceService = new InvoiceService();
        double distance=2.0;
        int time=5;
        double fare=invoiceService.getFair(InvoiceService.user.NORMAL,distance,time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance=0.1;
        int time=1;
        double fare= invoiceService.getFair(InvoiceService.user.NORMAL,distance,time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRide_ShouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();
        Ride[] ride = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary invoiceSummary= invoiceService.getFair(InvoiceService.user.NORMAL,ride);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedinvoiceSummary,invoiceSummary);
    }

    @Test
    public void givenUserWithPreminumRide_ShouldReturnTotal_Fare() {
        InvoiceService invoiceService = new InvoiceService();
        double distance=2.0;
        int time=5;
        double fare=invoiceService.getFair(InvoiceService.user.PERMIUM,distance,time);
        Assert.assertEquals(40,fare,0.0);
    }

    @Test
    public void givenUserWithPreminumMultipleRide_ShouldReturnInvoiceSummary() {
        InvoiceService invoiceService = new InvoiceService();
        String userId="abc@gmail.com";
        Ride[] ride = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        invoiceService.addRides(userId,ride);
        InvoiceSummary summary = invoiceService.getSummary(userId, InvoiceService.user.PERMIUM);
        InvoiceSummary expectedinvoiceSummary = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedinvoiceSummary,summary);
    }
}
