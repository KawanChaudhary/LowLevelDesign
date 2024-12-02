package ElevatorSystem.Models;

import ElevatorSystem.Constants.Direction;

public class Floor {
    private final int number;
    private final ExternalButton externalButton;

    public Floor(int floor){
        this.number = floor;
        externalButton = new ExternalButton();
    }

    public void pressButton(Direction direction){
        // call the elevator:
        externalButton.pressButton(number, direction);
    }
}
