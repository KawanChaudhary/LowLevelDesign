package ElevatorSystem.Models;

import ElevatorSystem.Constants.Direction;
import ElevatorSystem.Dispatcher.ExternalButtonDispatcher;

public class ExternalButton {
    private final ExternalButtonDispatcher externalButtonDispatcher;

    public ExternalButton(){
        externalButtonDispatcher = new ExternalButtonDispatcher();
    }

    public void pressButton(int floor, Direction dir){
        externalButtonDispatcher.submitRequest(floor, dir);
    }
}
