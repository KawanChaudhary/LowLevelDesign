package ElevatorSystem.Models;

import ElevatorSystem.Constants.Direction;
import ElevatorSystem.Constants.Status;

public class ElevatorCar {
    private final int id;
    private final Door door;
    private Display display;
    private final InternalButton internalButton;
    private Status status;
    private int currentFloor;
    private Direction direction;

    public ElevatorCar(int id, InternalButton internalButton){
        this.id = id;
        this.door = new Door();
        this.display = new Display(0, Direction.NONE);
        this.internalButton = internalButton;
        this.status = Status.IDLE;
        this.currentFloor = 0;
        this.direction = Direction.NONE;
    }

//    public void pressButton(int destination){
//
//    }

    public void move(int destination, Direction dir){
        int startFloor = this.currentFloor;
        int change = dir == Direction.DOWN ? -1 : 1;

        while(startFloor != destination){
            setDisplay();
            showDisplay();
            startFloor += change;
            setCurrentFloor(startFloor);
        }
    }

    public void showDisplay(){
        this.display.showDisplay();
    }

//    Getter and setter

    public InternalButton getInternalButton(){
        return internalButton;
    }

    public Display getDisplay(){
        return display;
    }

    public Door getDoor(){
        return door;
    }

    public int getId(){
        return id;
    }

    public Status getStatus(){
        return status;
    }

    public int getCurrentFloor(){
        return currentFloor;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setDisplay(){
        this.display.setDisplay(currentFloor, direction);
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public void setCurrentFloor(int floor){
        this.currentFloor = floor;
    }

    public void setDisplay(Display display){
        this.display = display;
    }
}
