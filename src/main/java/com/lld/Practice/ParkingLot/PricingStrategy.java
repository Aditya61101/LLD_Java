package com.lld.Practice.ParkingLot;

import java.time.Duration;

public interface PricingStrategy {
    double calculateFee(Duration duration, int rate);
}
