package vehicles;

public class Truck extends Vehicle implements IVehicle{
    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    public void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption+1.6);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters*0.95);
    }
}
