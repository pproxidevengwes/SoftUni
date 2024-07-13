package shoppingSpree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputPersons = scanner.nextLine().split(";");

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        for (String s : inputPersons) {
            String[] personData = s.split("=");
            String personName = personData[0].trim();
            Person person = new Person(personName, Double.parseDouble(personData[1]));
            people.putIfAbsent(personName, person);
        }

        String[] productsData = scanner.nextLine().split(";");

        for (String p : productsData) {
            String[] productInfo = p.split("=");
            String productName = productInfo[0].trim();
            Product product = new Product(productName, Double.parseDouble(productInfo[1]));
            products.putIfAbsent(productName, product);
        }

        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] tokens = line.split(" ");
            String personName = tokens[0].trim();
            String productName = tokens[1].trim();

            Person person = people.get(personName);
            Product product = products.get(productName);

            person.buyProduct(product);
            line = scanner.nextLine();
        }

        people.entrySet().forEach(p-> System.out.println(p.toString()));
    }
}
