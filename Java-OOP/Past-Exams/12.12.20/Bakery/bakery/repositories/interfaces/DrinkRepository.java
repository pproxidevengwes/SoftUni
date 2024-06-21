package bakery.repositories.interfaces;

import bakery.repositories.interfaces.Repository;

public interface DrinkRepository<T> extends Repository<T> {
    T getByNameAndBrand(String drinkName,String drinkBrand);
}
