package com.lld.Practice.ParkingLot;

import com.lld.Practice.ParkingLot.vehicles.Car;

public class Client {
    public static void main(String[] args) {
        // Start your implementation here
        ParkingLot parkingLot = new ParkingLot();
        // adding 5 floors
        for(int i=1; i<5; i++) {
            Floor floor = parkingLot.addFloor();
            // adding 10 car spots
            int it = 10;
            while(it > 0) {
                floor.addParkingSpot(VehicleType.CAR);
                it-=1;
            }
            // adding 7 bike spots
            it = 7;
            while(it > 0) {
                floor.addParkingSpot(VehicleType.BIKE);
                it-=1;
            }
            // adding 5 truck spots
            it = 5;
            while(it > 0) {
                floor.addParkingSpot(VehicleType.TRUCK);
                it-=1;
            }
        }

        Vehicle car = new Car();
        Ticket newTicket = parkingLot.assignSpot(car);
        System.out.println("Ticket id: " + newTicket.id);

        double fee = parkingLot.removeVehicle(newTicket.id);
        System.out.println("fee: "+ fee);
    }
}
