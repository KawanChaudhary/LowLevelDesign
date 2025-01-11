package ElevatorSystem.ElevatorSystem;

// import ElevatorSystem.Building.Building;
import ElevatorSystem.ElevatorController.ElevatorController;
import ElevatorSystem.Models.Floor;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private static ElevatorSystem instance;
    private final List<ElevatorController> elevatorControllerList;
    private final List<Floor> floorList;

    private ElevatorSystem(){
        elevatorControllerList = new ArrayList<>();
        floorList = new ArrayList<>();
    }

    public static synchronized ElevatorSystem getInstance(){
        if(instance == null){
            return new ElevatorSystem();
        }
        return instance;
    }

    public void addElevator(ElevatorController elevatorController){
        elevatorControllerList.add(elevatorController);
    }

    public void removeElevator(ElevatorController elevatorController){
        elevatorControllerList.remove(elevatorController);
    }

    public void addFloor(Floor floor){
        floorList.add(floor);
    }

    public void removeFloor(Floor floor){
        floorList.remove(floor);
    }

    public List<ElevatorController> getElevatorControllerList(){
        return this.elevatorControllerList;
    }

    public List<Floor> getFloorList(){
        return this.floorList;
    }
}
