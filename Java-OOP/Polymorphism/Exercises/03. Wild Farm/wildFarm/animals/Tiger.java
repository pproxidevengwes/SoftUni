package wildFarm.animals;

import wildFarm.foods.Food;
import wildFarm.foods.Vegetable;

public class Tiger extends Felime {
    public Tiger(String name, String type, double weight, String region) {
        super(name, type, weight, region);
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Vegetable) {
            throw new IllegalArgumentException(
                    "Tigers are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }
}
