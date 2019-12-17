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

    public InvoiceSummary getFair(user userType,Ride[] ride) {
        double totalFare=0;
        for (Ride rides: ride) {
            totalFare += this.getFair(userType,rides.distance, rides.time);
        }
        return new InvoiceSummary(ride.length,totalFare);
    }

    public double getFair(user type, double distance, int time) {
        switch (type) {
            case NORMAL:
                return getFair(distance,time,NORMAL_COST_PER_TIME,NORMAL_MINUMUM_COST_PER_KILOMETER,NORMAL_MINIMUM_FARE);
            case PERMIUM:
                return getFair(distance,time,PERMIUM_COST_PER_TIME,PERMIUM_MINUMUM_COST_PER_KILOMETER,PREMIUM_MINIMUM_FARE);
        }
        return 0;
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

    public InvoiceSummary getSummary(String userId, user userType) {
        return this.getFair(userType,rideRepository.getRide(userId));
    }
}
