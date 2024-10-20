package ParkingLot.ParkingSpot;

import ParkingLot.Vehicle.Vehicle;
import ParkingLot.Vehicle.VehicleType;

public class ParkingSpot {
    private final String spot;
    private final VehicleType spotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spot, VehicleType spotType){
        this.spot = spot;
        this.spotType = spotType;
    }

    public synchronized boolean isAvailable(){
        return this.parkedVehicle == null;
    }

    public synchronized void    parkVehicle(Vehicle vehicle){
        if(isAvailable() && vehicle.getType() == this.spotType){
            this.parkedVehicle = vehicle;
        }
        else{
            throw new IllegalArgumentException("Invalid vehicle type or spot already occupied");
        }
    }

    public synchronized void unParkVehicle(){
        parkedVehicle = null;
    }

    public String getSpot(){
        return spot;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public VehicleType getSpotType(){
        return spotType;
    }
}
