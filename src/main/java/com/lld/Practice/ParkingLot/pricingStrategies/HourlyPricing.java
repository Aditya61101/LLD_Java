package com.lld.Practice.ParkingLot.pricingStrategies;
import com.lld.Practice.ParkingLot.PricingStrategy;

import java.time.Duration;

public class HourlyPricing implements PricingStrategy {
    @Override
    public double calculateFee(Duration duration, int rate) {
        long hours = duration.toHours();
        return hours*rate;
    }
}
