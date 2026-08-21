package com.lld.Practice.ParkingLot.vehicles;

import com.lld.Practice.ParkingLot.Vehicle;
import com.lld.Practice.ParkingLot.VehicleType;

public class Truck implements Vehicle {
    public VehicleType getVehicleType() {
        return VehicleType.TRUCK;
    }
    public int getPricingRateInHour() {
        return 40;
    }
}