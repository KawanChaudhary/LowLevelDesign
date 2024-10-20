package ParkingLot.Gates;

import ParkingLot.Level.LevelManager;
import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Ticket.Ticket;
import ParkingLot.Vehicle.Vehicle;

public class EntranceGate {
    private final LevelManager levelManager;

    public EntranceGate(LevelManager _levelManager){
        levelManager = _levelManager;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){
       return levelManager.parkVehicle(vehicle);
    }

    public Ticket generateTicket(int id, ParkingSpot parkingSpot, Vehicle vehicle){
        return new Ticket(id, parkingSpot, vehicle);
    }
}
