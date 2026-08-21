package com.lld.Practice.ParkingLot.Factories;

import com.lld.Practice.ParkingLot.PricingStrategy;
import com.lld.Practice.ParkingLot.VehicleType;
import com.lld.Practice.ParkingLot.pricingStrategies.HourlyPricing;

public class PricingFactory {
    public static PricingStrategy createStrategy(VehicleType vt) {
        return switch (vt) {
            case vt.CAR -> new HourlyPricing();
            case vt.BIKE -> new HourlyPricing();
            case vt.TRUCK -> new HourlyPricing();
            default -> throw new IllegalArgumentException("Vehicle is of unknown type.");
        };
    }
}
