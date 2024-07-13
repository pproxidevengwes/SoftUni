package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public class Salad extends Food implements HealthyFood {
    public Salad(String name, double price) {
        super(name, 150, price);
    }
}
