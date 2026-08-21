package com.lld.Practice.ParkingLot;

import java.util.UUID;

public class ParkingSpot {
    String id;
    VehicleType spotType;
    boolean isOccupied;

    ParkingSpot(VehicleType spotType) {
        this.id = "PS-" + UUID.randomUUID().toString().substring(0, 8);
        this.isOccupied = false;
        this.spotType = spotType;
    }

    boolean isAvailable() {
        return !this.isOccupied;
    }
    void changeAvailability() {
        this.isOccupied = !this.isOccupied;
    }

    boolean isCompatible(VehicleType vt) {
        return vt == this.spotType;
    }
}
