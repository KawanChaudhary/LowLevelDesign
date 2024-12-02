package ElevatorSystem.Dispatcher;

import ElevatorSystem.Constants.Direction;
import ElevatorSystem.ElevatorSystem.ElevatorSystem;

public class ExternalButtonDispatcher {
    private final ElevatorSystem elevatorSystem;
    public ExternalButtonDispatcher(){
        this.elevatorSystem = ElevatorSystem.getInstance();
    }
    public void submitRequest(int floor, Direction dir){
        // need to implement
    }
}
