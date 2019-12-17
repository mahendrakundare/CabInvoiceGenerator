package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void givenUserNameWithRideShouldReturnInvoiceSummary() {
        RideRepository rideRepository = new RideRepository();
        String userId="abc@gmail.com";
        Ride[] ride = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        rideRepository.addRide(userId,ride);
        Assert.assertArrayEquals(ride,rideRepository.getRide(userId));
    }

    @Test
    public void givenUserNameWithNoRidesShouldThrowException() {
        RideRepository rideRepository = new RideRepository();
        String userId="123@gmail.com";
        Ride[] rides = {};
        rideRepository.addRide(userId,rides);
        Assert.assertEquals(0,rideRepository.getRide(userId).length);
    }
}
