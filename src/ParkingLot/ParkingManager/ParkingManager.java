package ParkingLot.ParkingManager;

import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Vehicle.Vehicle;

import java.util.List;

public abstract class ParkingManager {
    protected final List<ParkingSpot> spots;

    public ParkingManager(List<ParkingSpot> spots){
        this.spots = spots;
    }

    abstract ParkingSpot findParkingSpace();

    public ParkingSpot parkVehicle(Vehicle vehicle){
        ParkingSpot spot = findParkingSpace();
        spot.parkVehicle(vehicle);
        return spot;
    }

    public void unParkVehicle(Vehicle vehicle){
        for(ParkingSpot spot: spots){
            if(spot.getParkedVehicle() == vehicle){
                spot.unParkVehicle();
                break;
            }
        }
    }

}
