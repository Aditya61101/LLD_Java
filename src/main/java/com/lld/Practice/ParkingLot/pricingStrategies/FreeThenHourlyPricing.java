package com.lld.Practice.ParkingLot.pricingStrategies;

import com.lld.Practice.ParkingLot.PricingStrategy;

import java.time.Duration;

public class FreeThenHourlyPricing implements PricingStrategy {
    @Override
    public double calculateFee(Duration duration, int rate) {
        long hours = duration.toHours();
        // assuming for first 2 hrs parking is free
        if(hours<=2) return 0.0;

        return (hours-2)*rate;
    }
}
