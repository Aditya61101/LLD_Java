package com.lld.Practice.ParkingLot.vehicles;

import com.lld.Practice.ParkingLot.Vehicle;
import com.lld.Practice.ParkingLot.VehicleType;

public class Car implements Vehicle {
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
    public int getPricingRateInHour() {
        return 30;
    }
}
