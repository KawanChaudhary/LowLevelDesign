package ParkingLot.Vehicle;

public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;

    Vehicle(String licensePlate, VehicleType type){
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public VehicleType getType(){
        return this.type;
    }

    public String getLicensePlate(){
        return licensePlate;
    }
}
