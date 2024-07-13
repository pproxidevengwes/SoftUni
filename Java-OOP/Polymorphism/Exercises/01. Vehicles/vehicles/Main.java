package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] carData = scanner.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carData[1]),
                Double.parseDouble(carData[2]));
      
        String[] truckData = scanner.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckData[1]),
                Double.parseDouble(truckData[2]));

        int n = Integer.parseInt(scanner.nextLine());
        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String vehicle = tokens[1];
          
            if (tokens[0].equals("Drive")) {
                double dist = Double.parseDouble(tokens[2]);
              
                if (vehicle.equals("Car")) {
                    car.driving(dist);
                } else if (vehicle.equals("Truck")) {
                    truck.driving(dist);
                }
            } else if (tokens[0].equals("Refuel")) {
                double liters = Double.parseDouble(tokens[2]);
              
                if (vehicle.equals("Car")) {
                    car.refuel(liters);
                } else if (vehicle.equals("Truck")) {
                    truck.refuel(liters);
                }
            }

        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
