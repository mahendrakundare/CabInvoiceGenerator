package com.cabinvoicegenerator;

public class InvoiceService {
    private static final int COST_PER_TIME = 1;
    private static final double MINUMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_FARE = 5;
    RideRepository rideRepository=new RideRepository();

    public double getFair(double distance, int time) {
        double totalFare = distance * MINUMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if(totalFare<MINIMUM_FARE)
            return MINIMUM_FARE;
        return totalFare;

    }

    public InvoiceSummary getFair(Ride[] ride) {
        double totalFare=0;
        for (Ride rides: ride) {
             totalFare += this.getFair(rides.distance, rides.time);
        }
        return new InvoiceSummary(ride.length,totalFare);
    }

    public void addRides(String userId, Ride[] ride) {
        rideRepository.addRide(userId,ride);
        return;
    }

    public InvoiceSummary getSummary(String userId) {
        return this.getFair(rideRepository.getRide(userId));
    }
}
