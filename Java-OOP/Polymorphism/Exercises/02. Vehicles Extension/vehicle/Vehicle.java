package vehicles;

import java.text.DecimalFormat;
import java.util.Locale;

public class Vehicle implements IVehicle {

    protected double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;


    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.setTankCapacity(tankCapacity);
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

    public double getTankCapacity() {
        return this.tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
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
    public void driveEmpty(double distance) {

    }

    @Override
    public void refuel(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        } else if (liters + this.fuelQuantity > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %.2f", getClass().getSimpleName(), getFuelQuantity());
    }
}
