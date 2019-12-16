package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
    Map<String, ArrayList<Ride>> ridesList=null;

    public RideRepository(){
        this.ridesList=new HashMap<>();
    }

    public void addRide(String userId, Ride[] ride) {
        this.ridesList.put(userId,new ArrayList<>(Arrays.asList(ride)));
    }

    public Ride[] getRide(String userId) {
        return this.ridesList.get(userId).toArray(new Ride[0]);
    }
}

