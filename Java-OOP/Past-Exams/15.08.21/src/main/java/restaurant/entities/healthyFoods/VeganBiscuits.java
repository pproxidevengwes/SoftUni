package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public class VeganBiscuits extends Food implements HealthyFood {
    public VeganBiscuits(String name, double price) {
        super(name, 205, price);
    }
}
