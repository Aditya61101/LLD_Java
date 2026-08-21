package com.lld.Practice.ParkingLot.vehicles;

import com.lld.Practice.ParkingLot.Vehicle;
import com.lld.Practice.ParkingLot.VehicleType;

public class Bike implements Vehicle {
    public VehicleType getVehicleType() {
        return VehicleType.BIKE;
    }
    public int getPricingRateInHour() {
        return 20;
    }
}
