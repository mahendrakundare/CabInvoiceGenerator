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
}
