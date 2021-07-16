package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vehicle car=setVehicle(scanner.nextLine());
        Vehicle truck=setVehicle(scanner.nextLine());
        Vehicle bus=setVehicle(scanner.nextLine());

        int n = Integer.parseInt(scanner.nextLine());
        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String vehicle = tokens[1];
            try {
                switch (tokens[0]) {
                    case "Drive": {
                        double dist = Double.parseDouble(tokens[2]);
                        if (vehicle.equals("Car")) {
                            car.driving(dist);
                        } else if (vehicle.equals("Truck")) {
                            truck.driving(dist);
                        } else if (vehicle.equals("Bus")) {
                            bus.driving(dist);
                        }
                        break;
                    }
                    case "DriveEmpty": {
                        double dist = Double.parseDouble(tokens[2]);
                        bus.driveEmpty(dist);
                        break;
                    }
                    case "Refuel":
                        double liters = Double.parseDouble(tokens[2]);
                        if (vehicle.equals("Car")) {
                            car.refuel(liters);
                        } else if (vehicle.equals("Truck")) {
                            truck.refuel(liters);
                        } else if (vehicle.equals("Bus")) {
                            bus.refuel(liters);
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
        System.out.println(bus.toString());
    }

    public static Vehicle setVehicle(String input) {
        String[] vehicleData = input.split("\\s+");
        String vehicle = vehicleData[0];
        double initialFuel = Double.parseDouble(vehicleData[1]);
        double consumption = Double.parseDouble(vehicleData[2]);
        double tankCapacity = Double.parseDouble(vehicleData[3]);
        switch (vehicle) {
            case "Car":
                return new Car(initialFuel, consumption, tankCapacity);
            case "Truck":
                return new Truck(initialFuel, consumption, tankCapacity);
            case "Bus":
                return new Bus(initialFuel, consumption, tankCapacity);
            default:
                return new Vehicle(initialFuel, consumption, tankCapacity);
        }
    }
}
