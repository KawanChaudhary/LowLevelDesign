package ParkingLot.CostComputation;

import ParkingLot.Ticket.Ticket;
import ParkingLot.Vehicle.VehicleType;

import java.time.Duration;

public class CostComputation {

    public int calculateParkingCost(Ticket ticket) {
        if (ticket.getExitTime() == null) {
            throw new IllegalStateException("Exit time must be set before calculating the cost.");
        }

        VehicleType vehicleType = ticket.getVehicle().getType();
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        double totalCost = (hours * vehicleType.getCostPerHour()) + (minutes * vehicleType.getCostPerMinute());

        return (int) ((int)Math.round(totalCost * 100.0) / 100.0);
    }

}
