package ParkingLot.Level;

import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Level {
    private final int floor;
    private final List<ParkingSpot> parkingSpots;

    public Level(int floor, List<LevelDetails> levelDetails){
        this.parkingSpots = new ArrayList<>();
        this.floor = floor;
        for(LevelDetails ld: levelDetails){
            for(int i=0; i<ld.totalSpots; i++){
                String spot = "L" + floor + "-" + ld.parkingType + " " + i;
                parkingSpots.add(new ParkingSpot(spot, ld.parkingType));
            }
        }
    }

    public List<ParkingSpot> getParkingSlotsEqualsVehicleType(VehicleType type){
        return parkingSpots.stream().filter(spot -> spot.getSpotType().equals(type))
                .collect(Collectors.toList());
    }

    public List<ParkingSpot> getAvailableParkingSpotsEqualsVehicleType(VehicleType type){
        return parkingSpots.stream().filter(spot -> (spot.getSpotType().equals(type) && spot.isAvailable()))
                .collect(Collectors.toList());
    }

}
