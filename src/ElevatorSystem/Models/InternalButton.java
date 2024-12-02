package ElevatorSystem.Models;

import ElevatorSystem.Dispatcher.InternalButtonDispatcher;

import java.util.ArrayList;
import java.util.List;

public class InternalButton {
    private final List<Integer> floors;
    private final InternalButtonDispatcher internalButtonDispatcher;

    public InternalButton(){
        internalButtonDispatcher = new InternalButtonDispatcher();
        floors = new ArrayList<>();
    }

    void pressButton(int destination, ElevatorCar elevatorCar){
        // check if destination is in the list of available floors:
        // submit request
        floors.add(destination);
        System.out.println("Pressed floor " + destination + " from elevator " + elevatorCar.getId());
        internalButtonDispatcher.submitRequest(destination, elevatorCar);
    }
}
