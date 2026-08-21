package com.lld.Practice.ParkingLot.Factories;

import com.lld.Practice.ParkingLot.Vehicle;
import com.lld.Practice.ParkingLot.VehicleType;
import com.lld.Practice.ParkingLot.vehicles.Bike;
import com.lld.Practice.ParkingLot.vehicles.Car;
import com.lld.Practice.ParkingLot.vehicles.Truck;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vt) {
        switch(vt) {
            case vt.CAR:
                return new Car();
            case vt.BIKE:
                return new Bike();
            case vt.TRUCK:
                return new Truck();
            default:
                throw new IllegalArgumentException("Vehicle is of unknown type.");
        }
    }
}