package restaurant.entities.healthyFoods;

import restaurant.common.ExceptionMessages;

public class Food {
    private String name;
    private double portion;
    private double price;

    public Food(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COUNTER);
        }
        this.portion = portion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fg - %.2f", this.name, this.portion, this.price);
    }
}
