package ParkingLot.ParkingManager;

import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Vehicle.VehicleType;

import java.util.List;

public class ParkingManagerFactory {
    public ParkingManager getParkingManager(VehicleType type, List<ParkingSpot> spots){
        if(type == VehicleType.Bike){
            return new BikeParkingManager(spots);
        }
        else if(type == VehicleType.Car){
            return new CarParkingManager(spots);
        }
        else if(type == VehicleType.Truck){
            return new TruckParkingManager(spots);
        }
        else{
            throw new IllegalArgumentException("Invalid vehicle type!");
        }
    }
}
