package ParkingLot;

import ParkingLot.Gates.EntranceGate;
import ParkingLot.Gates.ExitGate;
import ParkingLot.Level.LevelManager;
import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Ticket.Ticket;
import ParkingLot.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private final LevelManager levelManager;
    private final List<Ticket> tickets;
    private final EntranceGate entranceGate;
    private final ExitGate exitGate;

    private ParkingLot(){
        levelManager = new LevelManager();
        tickets = new ArrayList<>();
        entranceGate = new EntranceGate(levelManager);
        exitGate = new ExitGate(levelManager);
    }

    public static synchronized ParkingLot getInstance(){
        if(instance == null){
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(){
        levelManager.addLevel();
    }

    public void parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot = entranceGate.parkVehicle(vehicle);
        if(parkingSpot == null){
            System.out.println("Sorry, parking is full.");
        }
        else{
            int id = tickets.size();
            Ticket ticket = entranceGate.generateTicket(id, parkingSpot, vehicle);
            tickets.add(ticket);
            System.out.println(ticket);
        }
    }

    public void unParkVehicle(int id){
        Ticket ticket = null;
        for(Ticket t: tickets) {
            if(t.getId() == id){
                ticket = t.getVehicle() != null ? t : null;
                break;
            }
        }
        if(ticket == null){
            System.out.println("Invalid ticket id.");
        }
        else{
             exitGate.unParkVehicle(ticket);
        }
    }

}