package restaurant.entities.drinks;

import restaurant.entities.drinks.interfaces.Beverages;

public class Smoothie extends BaseBeverage implements Beverages {
    public Smoothie(String name, int counter, String brand) {
        super(name, counter, 4.50, brand);
    }
}
