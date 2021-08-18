package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    private Collection<HealthyFood> models;

    public HealthFoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.models.stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);

    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(models);

    }

    @Override
    public void add(HealthyFood entity) {
        this.models.add(entity);

    }
}
