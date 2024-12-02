package ElevatorSystem.Models;

import ElevatorSystem.Constants.Direction;

public class Display {
    private int floor;
    private Direction dir;

    public Display(int floor, Direction dir){
        this.floor = floor;
        this.dir = dir;
    }

    public int getFloor(){
        return floor;
    }

    public Direction getDir(){
        return dir;
    }

    public void setDisplay(int floor, Direction dir){
        this.floor = floor;
        this.dir = dir;
    }

    public void showDisplay(){
        System.out.println(floor + " " + dir);
    }
}
