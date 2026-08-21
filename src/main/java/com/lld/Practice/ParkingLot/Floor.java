package com.lld.Practice.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Floor {
    String id;
    List<ParkingSpot> parkingSpots;
    Floor() {
        this.id = "F-" + UUID.randomUUID().toString().substring(0, 8);
        this.parkingSpots = new ArrayList<>();
    }
    void addParkingSpot(VehicleType vt) {
        parkingSpots.add(new ParkingSpot(vt));
    }
    ParkingSpot findCompatibleSpot(Vehicle v) {
        for(ParkingSpot ps:parkingSpots) {
            if(ps.isAvailable() && ps.isCompatible(v.getVehicleType())) {
                ps.changeAvailability();
                return ps;
            }
        }
        return null;
    }
}
