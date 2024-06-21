package wildFarm.animals;

import wildFarm.foods.Food;

import java.text.DecimalFormat;

public abstract class Animal {
    private String name;
    private String type;
    private double weight;
    private String region;
    private int foodEaten;

    protected Animal(String name, String type, double weight, String region) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.region = region;
    }

    protected String getType() {
        return this.type;
    }

    public abstract void makeSound();

    public void eat(Food food) {
        this.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.##");
        return String.format(
                "%s[%s, %s, %s, %d]",
                this.type,
                this.name,
                df.format(this.weight),
                this.region,
                this.foodEaten);
    }
}
