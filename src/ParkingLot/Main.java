package ParkingLot;

import ParkingLot.Vehicle.Car;
import ParkingLot.Vehicle.Vehicle;
import TicTacToe.TicTacToe;

public class Main {
    public static void main(String[] args){
        ParkingLot parkingLot = ParkingLot.getInstance();

//       Add a new level
        parkingLot.addLevel();

//       park first vehicle
        Vehicle car = new Car("ABC-123");
        parkingLot.parkVehicle(car);

//        Add another level
        parkingLot.addLevel();
        Vehicle car1 = new Car("ABC-1234");
        parkingLot.parkVehicle(car1);
    }
}
