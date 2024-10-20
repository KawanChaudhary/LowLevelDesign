package ParkingLot.Level;

import ParkingLot.Vehicle.VehicleType;

public class LevelDetails {
    public final int totalSpots;
    public final VehicleType parkingType;

    public LevelDetails(int totalSpots, VehicleType vehicleType){
        this.totalSpots = totalSpots;
        this.parkingType = vehicleType;
    }
}
