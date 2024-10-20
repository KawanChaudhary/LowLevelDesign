package ParkingLot.ParkingManager;

import ParkingLot.ParkingSpot.ParkingSpot;

import java.util.List;

public class TruckParkingManager extends ParkingManager {
    public TruckParkingManager(List<ParkingSpot> spots){
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
