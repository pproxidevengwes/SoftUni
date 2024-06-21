import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Engine> engines = new HashMap<>();
        List<Car> cars = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());
        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            int power = Integer.parseInt(tokens[1]);
            int displacenent = 0;
            String efficiency = "n/a";
            if (tokens.length == 3) {

                try {
                    displacenent = Integer.parseInt(tokens[2]);
                    Engine engine = new Engine(model, power, displacenent);
                    engines.put(model, engine);
                } catch (NumberFormatException e) {
                    efficiency = tokens[2];
                    Engine engine = new Engine(model, power, efficiency);
                    engines.put(model, engine);
                }
                Engine engine = new Engine(model, power, displacenent, efficiency);
                engines.put(model, engine);
            } else if (tokens.length == 4) {
                displacenent = Integer.parseInt(tokens[2]);
                efficiency = tokens[3];
                Engine engine = new Engine(model, power, displacenent, efficiency);
                engines.put(model, engine);

            } else {
                Engine engine = new Engine(model, power, displacenent, efficiency);
                engines.put(model, engine);
            }

        }

        int m = Integer.parseInt(scanner.nextLine());
        while (m-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            String engine = tokens[1];
            int weight = 0;
            String color = "n/a";
            if (tokens.length == 2) {
                Car car = new Car(model, engines.get(engine), weight);
                cars.add(car);
            } else if (tokens.length == 3) {
                try {
                    weight = Integer.parseInt(tokens[2]);
                    Car car = new Car(model, engines.get(engine), weight);
                    cars.add(car);
                } catch (NumberFormatException e) {
                    color = tokens[2];
                    Car car = new Car(model, engines.get(engine), color);
                    cars.add(car);
                }
            } else {
                weight = Integer.parseInt(tokens[2]);
                color = tokens[3];
                Car car = new Car(model, engines.get(engine), weight, color);
                cars.add(car);
            }
        }
        for (Car car : cars) {
            System.out.println(car.getModel() + ":");
            System.out.println(car.getEngine().toString());
            System.out.println(car.toString());
        }
    }
}
