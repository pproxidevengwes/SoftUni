package shoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalStateException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void buyProduct(Product product) {
        double cost = product.getCost();
        double calculation = this.money - cost;
        if (calculation < 0) {
            System.out.printf("%s can't afford %s%n", this.name, product.getName());
        } else {
            setMoney(calculation);
            this.products.add(product);
            System.out.printf("%s bought %s%n", this.name, product.getName());
        }

    }

    private String productStatusMessage() {
        if (this.products.isEmpty()) {
            return "Nothing bought";
        }
        List<String> productsNames = this.products.stream().map(Product::getName).collect(Collectors.toList());
        return productsNames.toString().replaceAll("[\\[\\]]", "");
    }

    @Override
    public String toString() {
        return this.name + " - " + productStatusMessage();
    }
}
