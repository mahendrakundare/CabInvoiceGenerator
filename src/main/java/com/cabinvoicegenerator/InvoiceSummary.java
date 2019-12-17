package com.cabinvoicegenerator;

import java.util.Objects;

public class InvoiceSummary {
    private final int numOfRides;
    private final double totalFare;
    private final double averageFare;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.numOfRides=totalRides;
        this.totalFare=totalFare;
        this.averageFare=this.totalFare/this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numOfRides == that.numOfRides &&
                totalFare == that.totalFare &&
                averageFare == that.averageFare;
    }


}
