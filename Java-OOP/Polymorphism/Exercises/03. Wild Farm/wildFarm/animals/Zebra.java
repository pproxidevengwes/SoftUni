package wildFarm.animals;

import wildFarm.foods.Food;
import wildFarm.foods.Meat;

public class Zebra extends Mammal {
    public Zebra(String name, String type, double weight, String region) {
        super(name, type, weight, region);
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat) {
            throw new IllegalArgumentException(
                    "Zebras are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }
}
