package ElevatorSystem.Dispatcher;

import ElevatorSystem.ElevatorController.ElevatorController;
import ElevatorSystem.ElevatorSystem.ElevatorSystem;
import ElevatorSystem.Models.ElevatorCar;

public class InternalButtonDispatcher {
    private final ElevatorSystem elevatorSystem;
    public InternalButtonDispatcher(){
        this.elevatorSystem = ElevatorSystem.getInstance();
    }

    public void submitRequest(int floor, ElevatorCar elevatorCar){
        for(ElevatorController elevatorController: elevatorSystem.getElevatorControllerList()){
            if(elevatorController.getId() == elevatorCar.getId()){
                elevatorController.request(floor, elevatorCar.getDirection());
            }
        }
    }
}
