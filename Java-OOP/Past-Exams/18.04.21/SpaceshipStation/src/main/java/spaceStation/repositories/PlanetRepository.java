package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlanetRepository {
    private List<Planet> planets;

    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }

    public Collection<Planet> getModels() {

        return Collections.unmodifiableCollection(this.planets);
    }


    public void add(Planet model) {
        this.planets.add(model);
    }


    public boolean remove(Planet model) {
        if(this.planets.contains(model)){
            this.planets.remove(model);
            return true;
        }
        return false;
    }


    public Planet findByName(String name) {
        for (Planet planet : this.planets) {
            if(planet.getName().equals(name))
                return planet;
        }
        return null;
    }
}
