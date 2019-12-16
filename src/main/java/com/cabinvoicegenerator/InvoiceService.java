package com.cabinvoicegenerator;

public class InvoiceService {
    public enum user {NORMAL,PERMIUM}
    private static final int NORMAL_COST_PER_TIME = 1;
    private static final double NORMAL_MINUMUM_COST_PER_KILOMETER = 10;
    private static final double NORMAL_MINIMUM_FARE = 5;
    private static final int PERMIUM_COST_PER_TIME =2;
    private static final double PERMIUM_MINUMUM_COST_PER_KILOMETER = 15;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    RideRepository rideRepository=new RideRepository();

    public InvoiceSummary getFair(Ride[] ride) {
        double totalFare=0;
        for (Ride rides: ride) {
             totalFare += this.getFair(rides.distance, rides.time);
        }
        return new InvoiceSummary(ride.length,totalFare);
    }

    public double getFair(double distance, int time) {
        double totalFare = distance * NORMAL_MINUMUM_COST_PER_KILOMETER + time * NORMAL_COST_PER_TIME;
        if(totalFare< NORMAL_MINIMUM_FARE)
            return NORMAL_MINIMUM_FARE;
        return totalFare;
    }

    public double getFair(user type, double distance, int time) {
        double totalFare=0;
        switch (type) {
            case NORMAL:
                return totalFare=getFair(distance,time,NORMAL_COST_PER_TIME,NORMAL_MINUMUM_COST_PER_KILOMETER,NORMAL_MINIMUM_FARE);
            case PERMIUM:
                return totalFare=getFair(distance,time,PERMIUM_COST_PER_TIME,PERMIUM_MINUMUM_COST_PER_KILOMETER,PREMIUM_MINIMUM_FARE);
        }
        return totalFare;
    }

    private double getFair(double distance, int time, int costPerTime, double costPerKilometer, double minimumFare) {
        double totalFare =distance*costPerKilometer+time*costPerTime;
        if (totalFare<minimumFare)
            return minimumFare;
        return totalFare;
    }

    public void addRides(String userId, Ride[] ride) {
        rideRepository.addRide(userId,ride);
        return;
    }

    public InvoiceSummary getSummary(String userId) {
        return this.getFair(rideRepository.getRide(userId));
    }
}
