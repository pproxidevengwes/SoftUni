package restaurant.entities.drinks;

import restaurant.entities.drinks.interfaces.Beverages;

public class Fresh extends BaseBeverage implements Beverages {
    public Fresh(String name, int counter, String brand) {
        super(name, counter, 3.50, brand);
    }
}
