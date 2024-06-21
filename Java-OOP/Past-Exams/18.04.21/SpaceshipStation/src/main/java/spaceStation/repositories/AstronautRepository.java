package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.*;

public class AstronautRepository  {
    private List<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    public Collection<Astronaut> getModels() {

        return Collections.unmodifiableCollection(this.astronauts);
    }


    public void add(Astronaut model) {
        this.astronauts.add(model);
    }


    public boolean remove(Astronaut model) {
        if(this.astronauts.contains(model)){
            this.astronauts.remove(model);
            return true;
        }
        return false;
    }


    public Astronaut findByName(String name) {
        for (Astronaut astronaut : this.astronauts) {
            if(astronaut.getName().equals(name)){
                return astronaut;
            }
        }
        return null;
    }
}
