package com.lld.Practice.ParkingLot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final List<Floor> floors;
    private final Map<String, Ticket> activeTickets;

    ParkingLot() {
        floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }
    public Floor addFloor() {
        Floor floor = new Floor();
        floors.add(floor);
        return floor;
    }
    // need to fulfill concurrency
    Ticket assignSpot(Vehicle v) throws IllegalStateException {
        ParkingSpot ps = this.findAvailableSpot(v);
        if(ps!=null) {
            // generate ticket and return ticket ID, can be done in a ticket service
            Ticket newTicket = new Ticket(v, ps, Instant.now());
            this.storeTicket(newTicket);
            return newTicket;
        }
        throw new IllegalStateException("Parking lot is full. Cannot park vehicle.");
    }

    double removeVehicle(String ticketId) {
        Ticket ticket = getTicket(ticketId);

        if(ticket==null) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }

        double fee = ticket.calculateFee();

        ticket.parkingSpot.changeAvailability();

        this.removeTicket(ticketId);

        return fee;
    }

    private Ticket getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }

    private void storeTicket(Ticket newTicket) {
        activeTickets.put(newTicket.getId(), newTicket);
    }

    private void removeTicket(String ticketId) {
        activeTickets.remove(ticketId);
    }

    private ParkingSpot findAvailableSpot(Vehicle v) {
        for(Floor floor:floors) {
            ParkingSpot ps = floor.findCompatibleSpot(v);
            if(ps!=null) return ps;
        }
        return null;
    }
}
