package vehicles;

public class Bus extends Vehicle implements IVehicle {
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public void driving(double distance) {
        super.setFuelConsumption(super.getFuelConsumption() + 1.4);
        super.driving(distance);
    }

    @Override
    public void driveEmpty(double distance) {
        super.setFuelConsumption(super.getFuelConsumption() - 1.4);
        super.driving(distance);
    }
}
