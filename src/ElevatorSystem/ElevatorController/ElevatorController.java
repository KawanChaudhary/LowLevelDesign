package ElevatorSystem.ElevatorController;

import ElevatorSystem.Constants.Direction;
import ElevatorSystem.Models.ElevatorCar;

public class ElevatorController {
    private final int id;
    private final ElevatorCar elevatorCar;

    public ElevatorController(int id, ElevatorCar elevatorCar){
        this.id = id;
        this.elevatorCar = elevatorCar;
    }

    public void request(int floor, Direction dir){

    }

    public int getId(){
        return id;
    }
}
