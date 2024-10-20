package ParkingLot.Ticket;
import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Ticket {
    private final int id;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private final ParkingSpot parkingSpot;
    private final Vehicle vehicle;

    public Ticket(int id, ParkingSpot parkingSpot, Vehicle vehicle) {
        if (parkingSpot == null || vehicle == null) {
            throw new IllegalArgumentException("Parking spot and vehicle cannot be null");
        }
        this.id = id;
        this.entryTime = LocalDateTime.now();
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public Temporal getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getId() {
        return id;
    }

    public Temporal getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime){
        this.exitTime = exitTime;
    }

    private String formatDateTime(LocalDateTime entryTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return entryTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "entryTime: " + formatDateTime(entryTime) +
                ", parkingSpot: " + parkingSpot.getSpot() +
                ", vehicle: " + vehicle.getLicensePlate() +
                '}';
    }
}
