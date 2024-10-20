package ParkingLot.Vehicle;

public enum VehicleType {
    Bike(10, 0.17),
    Car(20, 0.33),
    Truck(30, 0.50);

    private final int costPerHour;
    private final double costPerMinute;

    VehicleType(int costPerHour, double costPerMinute) {
        this.costPerHour = costPerHour;
        this.costPerMinute = costPerMinute;
    }

    public int getCostPerHour() {
        return costPerHour;
    }

    public double getCostPerMinute() {
        return costPerMinute;
    }
}