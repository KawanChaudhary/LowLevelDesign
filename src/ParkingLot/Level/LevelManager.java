package ParkingLot.Level;

import ParkingLot.ParkingManager.ParkingManager;
import ParkingLot.ParkingManager.ParkingManagerFactory;
import ParkingLot.ParkingSpot.ParkingSpot;
import ParkingLot.Ticket.Ticket;
import ParkingLot.Vehicle.Vehicle;
import ParkingLot.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelManager {
    private final List<Level> parkingFloors;
    private final ParkingManagerFactory parkingManagerFactory;

    public LevelManager(){
        parkingFloors = new ArrayList<>();
        parkingManagerFactory = new ParkingManagerFactory();
    }

    public void addLevel(){
        Scanner scanner = new Scanner(System.in);
        int floorNumber = parkingFloors.size();
        List<LevelDetails> levelDetails = new ArrayList<>();
        for (VehicleType vehicleType : VehicleType.values()) {
            boolean typeInputValid = false;
            while (!typeInputValid) {
                try {
                    System.out.print("Enter the number of parking spots for " + vehicleType + ": ");
                    int spotsForType = Integer.parseInt(scanner.nextLine());

                    if (spotsForType < 0) {
                        System.out.println("Parking spots for " + vehicleType + " cannot be negative.");
                    }
                    else {
                        levelDetails.add(new LevelDetails(spotsForType, vehicleType));
                        typeInputValid = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
        }
        parkingFloors.add(new Level(floorNumber, levelDetails));
        System.out.println("New parking L-" + floorNumber + " successfully added.");
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){

        for(Level lvl: parkingFloors){
            List<ParkingSpot> spots = lvl.getAvailableParkingSpotsEqualsVehicleType(vehicle.getType());
            if(!spots.isEmpty()){
                ParkingManager parkingManager = parkingManagerFactory.getParkingManager(vehicle.getType(), spots);
                return parkingManager.parkVehicle(vehicle);
            }
        }
        return null;
    }

    public int getFloorNumber(String parkingSpotLabel) {
        if (parkingSpotLabel != null && parkingSpotLabel.startsWith("L")) {
            try {
                String floorPart = parkingSpotLabel.substring(1).split("-")[0];
                return Integer.parseInt(floorPart);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid parking spot label format.");
            }
        }
        return -1;
    }

    public Level getFloorByIndex(int floor){
        if(floor < 0 || floor >= parkingFloors.size()){
            throw new NumberFormatException("Invalid floor number");
        }
        return parkingFloors.get(floor);
    }

    public void unParkVehicle(Ticket ticket){
        int floor = getFloorNumber(ticket.getParkingSpot().getSpot());
        Level lvl = getFloorByIndex(floor);
        List<ParkingSpot> parkingSpots = lvl.getParkingSlotsEqualsVehicleType(ticket.getVehicle().getType());

        ParkingManager parkingManager = parkingManagerFactory.getParkingManager(ticket.getVehicle().getType(), parkingSpots);
        parkingManager.unParkVehicle(ticket.getVehicle());
    }

}
