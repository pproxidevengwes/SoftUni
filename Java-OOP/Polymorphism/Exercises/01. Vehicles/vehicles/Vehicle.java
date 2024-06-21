package vehicles;

import java.text.DecimalFormat;

public class Vehicle implements IVehicle {

    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void driving(double distance) {
        if (this.getFuelQuantity() < this.getFuelConsumption() * distance) {
            System.out.printf("%s needs refueling\n", getClass().getSimpleName());
        } else {
            DecimalFormat decimalValue = new DecimalFormat("#.##");
            System.out.printf("%s travelled %s km\n", getClass().getSimpleName(), decimalValue.format(distance));
            this.fuelQuantity -= this.getFuelConsumption() * distance;
        }
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), getFuelQuantity());
    }
}
