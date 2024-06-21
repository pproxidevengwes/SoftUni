package spaceStation.core;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private AstronautRepository astronauts;
    private PlanetRepository planets;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronauts = new AstronautRepository();
        this.planets = new PlanetRepository();
        this.exploredPlanets = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        switch (type) {
            case "Biologist":
                Biologist biologist = new Biologist(astronautName);
                this.astronauts.add(biologist);
                break;
            case "Geodesist":
                Geodesist geodesist = new Geodesist(astronautName);
                this.astronauts.add(geodesist);
                break;

            case "Meteorologist":
                Meteorologist meteorologist = new Meteorologist(astronautName);
                this.astronauts.add(meteorologist);
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        return String.format("Successfully added %s: %s!", type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        PlanetImpl planet = new PlanetImpl(planetName);
        if (items != null) {
            for (String item : items) {
                planet.getItems().add(item);
            }
        }
        this.planets.add(planet);
        return String.format("Successfully added Planet: %s!", planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        for (Astronaut astronaut1 : this.astronauts.getModels()) {
            if (astronaut1.getName().equals(astronautName)) {
                this.astronauts.remove(astronaut1);
                return String.format("Astronaut %s was retired!", astronautName);
            }
        }
        throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> explorers = this.astronauts.getModels().stream()
                .filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet toExplore = this.planets.getModels().stream()
                .filter(p -> p.getName().equals(planetName)).collect(Collectors.toList()).get(0);
        Mission mission = new MissionImpl();
        mission.explore(toExplore, explorers);
        this.exploredPlanets++;
        explorers = explorers.stream().filter(e -> e.getOxygen() == 0).collect(Collectors.toList());
        return String.format("Planet: %s was explored! Exploration finished with %d dead astronauts!"
                , toExplore.getName(), explorers.size());


    }

    @Override
    public String report() {
        StringBuilder fill = new StringBuilder();
        fill.append(String.format("%s planets were explored!%n", this.exploredPlanets));
        fill.append("Astronauts info:").append(System.lineSeparator());
        for (Astronaut astronaut : astronauts.getModels()) {
            fill.append(astronaut.toString()).append(System.lineSeparator());
        }
        return fill.toString().trim();
    }
}
