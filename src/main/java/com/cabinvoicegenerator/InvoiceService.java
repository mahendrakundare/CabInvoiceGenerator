package com.cabinvoicegenerator;

public class InvoiceService {
    public double getFair(user type, double distance, int time) {
        double totalFare=0;
        if (user.NORMAL.equals(type)) {
            return totalFare = getFair(distance, time);
        }
        return  totalFare = distance * PERMIUM_MINUMUM_COST_PER_KILOMETER + time * PERMIUM_COST_PER_TIME;
    }

    public enum user {NORMAL,PERMIUM}
    private static final int NORMAL_COST_PER_TIME = 1;
    private static final double NORMAL_MINUMUM_COST_PER_KILOMETER = 10;
    private static final double NORMAL_MINIMUM_FARE = 5;
    private static final int PERMIUM_COST_PER_TIME =2;
    private static final double PERMIUM_MINUMUM_COST_PER_KILOMETER = 15;
    private static final double PREMIUM_MINIMUM_FARE = 5;

    RideRepository rideRepository=new RideRepository();

    public double getFair(double distance, int time) {

        double totalFare = distance * NORMAL_MINUMUM_COST_PER_KILOMETER + time * NORMAL_COST_PER_TIME;
        if(totalFare< NORMAL_MINIMUM_FARE)
            return NORMAL_MINIMUM_FARE;
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
