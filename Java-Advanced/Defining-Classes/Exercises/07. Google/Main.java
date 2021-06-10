import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> personMap = new HashMap<>();

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            String name = tokens[0];
            if (!personMap.containsKey(name)) {
                personMap.put(name, new Person());
            }

            String input = tokens[1];
            switch (input) {
                case "company":
                    String companyName = tokens[2];
                    String department = tokens[3];
                    double salary = Double.parseDouble(tokens[4]);
                    Company company = new Company(companyName, department, salary);
                    personMap.get(name).setCompany(company);
                    break;
                case "pokemon":
                    String pokemonName = tokens[2];
                    String pokemonType = tokens[3];
                    Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                    personMap.get(name).getPokemons().add(pokemon);
                    break;

                case "parents":
                    String parentName = tokens[2];
                    String parenBDay = tokens[3];
                    Parent parent = new Parent(parentName, parenBDay);
                    personMap.get(name).getParents().add(parent);
                    break;
                    
                case "children":
                    String childName = tokens[2];
                    String childBDay = tokens[3];
                    Child child = new Child(childName, childBDay);
                    personMap.get(name).getChildren().add(child);
                    break;
                    
                case "car":
                    String carModel = tokens[2];
                    int carSpeed = Integer.parseInt(tokens[3]);
                    Car car = new Car(carModel, carSpeed);
                    personMap.get(name).setCar(car);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }
            line = scanner.nextLine();
        }

        String searched = scanner.nextLine();
        System.out.println(searched);
        Person data = personMap.get(searched);
        System.out.println(data);
    }
}
