import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();
      
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String model = input[0];
            int engineSpeed = Integer.parseInt(input[1]);
            int enginePower = Integer.parseInt(input[2]);
            int cargoWeight = Integer.parseInt(input[3]);
            String cargoType = input[4];
            double tire1Pressure = Double.parseDouble(input[5]);
            int tire1Age = Integer.parseInt(input[6]);
            double tire2Pressure = Double.parseDouble(input[7]);
            int tire2Age = Integer.parseInt(input[8]);
            double tire3Pressure = Double.parseDouble(input[9]);
            int tire3Age = Integer.parseInt(input[10]);
            double tire4Pressure = Double.parseDouble(input[11]);
            int tire4Age = Integer.parseInt(input[12]);

            Engine engine = new Engine(engineSpeed, enginePower);
          
            Cargo cargo = new Cargo(cargoWeight, cargoType);
          
            Tire tire1 = new Tire(tire1Age, tire1Pressure);
            Tire tire2 = new Tire(tire2Age, tire2Pressure);
            Tire tire3 = new Tire(tire3Age, tire3Pressure);
            Tire tire4 = new Tire(tire4Age, tire4Pressure);
            Tire[] tires = new Tire[]{tire1, tire2, tire3, tire4};

            Car car = new Car(model, engine, cargo, tires);
            cars.add(car);
        }
        String type = scanner.nextLine();
        if (type.equals("fragile")) {
            for (Car car : cars) {
                Tire[] tires = car.getTires();
                for (Tire tire : tires) {
                    if (tire.getTirePressure() <= 1) {
                        System.out.println(car.getModel());
                        break;
                    }
                }
            }
        } else if (type.equals("flamable")) {
            cars.stream().filter(c -> c.getEngine().getPower() > 250)
                    .forEach(e -> System.out.println(e.getModel()));
        }


    }
}
