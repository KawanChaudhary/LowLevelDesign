package ParkingLot.ParkingManager;

import ParkingLot.ParkingSpot.ParkingSpot;

import java.util.List;

public class CarParkingManager extends ParkingManager {
    public CarParkingManager(List<ParkingSpot> spots){
        super((spots));
    }

    @Override
    ParkingSpot findParkingSpace() {
        for(ParkingSpot spot: spots){
            if(spot.isAvailable()){
                return spot;
            }
        }
        return null;
    }
}