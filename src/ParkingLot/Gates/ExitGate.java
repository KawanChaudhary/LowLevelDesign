package ParkingLot.Gates;

import ParkingLot.CostComputation.CostComputation;
import ParkingLot.Level.LevelManager;
import ParkingLot.Ticket.Ticket;

import java.time.LocalDateTime;

public class ExitGate {
    private final LevelManager levelManager;
    private final CostComputation costComputation;

    public ExitGate(LevelManager _levelManager){
        levelManager = _levelManager;
        costComputation = new CostComputation();
    }

    public void unParkVehicle(Ticket ticket){
        levelManager.unParkVehicle(ticket);
        closeTicket(ticket);
        int finalPrice = costComputation.calculateParkingCost(ticket);
        System.out.println("Please proceed with the payment: " + finalPrice);
    }

    public void closeTicket(Ticket ticket){
        // Add 2 hours and 20 minutes to the entry time to set the exit time
        LocalDateTime exitTime = LocalDateTime.now().plusHours(2).plusMinutes(20);
        ticket.setExitTime(exitTime);
    }
}
