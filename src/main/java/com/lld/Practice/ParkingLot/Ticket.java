package com.lld.Practice.ParkingLot;

import com.lld.Practice.ParkingLot.Factories.PricingFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Ticket {
    String id;
    Vehicle vehicle;
    ParkingSpot parkingSpot;
    // Instant is timezone independent
    private final Instant startTime;

    Ticket(Vehicle v, ParkingSpot ps, Instant startTime) {
        this.id = "T-" + UUID.randomUUID().toString().substring(0, 8);
        this.vehicle = v;
        this.parkingSpot = ps;
        this.startTime = startTime;
    }

    String getId() { return this.id; }

    double calculateFee() {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        int rate = vehicle.getPricingRateInHour();
        PricingStrategy ps = PricingFactory.createStrategy(vehicle.getVehicleType());
        return ps.calculateFee(duration, rate);
    }

}
