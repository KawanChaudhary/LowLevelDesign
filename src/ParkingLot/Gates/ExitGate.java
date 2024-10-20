package ParkingLot.Gates;

import ParkingLot.CostComputation.CostComputation;
import ParkingLot.Level.LevelManager;
import ParkingLot.Ticket.Ticket;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ExitGate {
    private final LevelManager levelManager;
    private final CostComputation costComputation;

    public ExitGate(LevelManager _levelManager){
        levelManager = _levelManager;
        costComputation = new CostComputation();
    }

    public int unParkVehicle(Ticket ticket){
        levelManager.unParkVehicle(ticket);
        closeTicket(ticket);
        return costComputation.calculateParkingCost(ticket);
    }

    public void closeTicket(@NotNull Ticket ticket){
        ticket.setExitTime(LocalDateTime.now());
    }
}
