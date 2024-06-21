package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Collection<Beverages> models;

    public BeverageRepositoryImpl() {
        this.models = new ArrayList<>();
    }


    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return this.models.stream().filter(d -> d.getName().equals(drinkName) && d.getBrand().equals(drinkBrand)).findFirst().orElse(null);

    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(models);

    }

    @Override
    public void add(Beverages entity) {
        this.models.add(entity);

    }
}
