package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<String> planetItems = (List<String>) planet.getItems();
        for (Astronaut astronaut : astronauts) {
            if(planet.getItems().isEmpty()){
                break;
            }
            while (astronaut.canBreath()){
                if(planet.getItems().isEmpty()){
                    break;
                }
                String item = planetItems.get(0);
                astronaut.getBag().getItems().add(item);
                planet.getItems().remove(item);
                astronaut.breath();
            }

        }
    }
}
