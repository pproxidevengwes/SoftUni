package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorers;
    private StateRepository states;
    private int exploredStatesCount;

    public ControllerImpl() {
        this.explorers = new ExplorerRepository();
        this.states = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type){
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        this.explorers.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(Arrays.asList(exhibits));
        this.states.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        if (this.explorers.getCollection().stream().noneMatch(a -> a.getName().equals(explorerName))){
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        Explorer explorerToRemove = this.explorers.byName(explorerName);
        this.explorers.remove(explorerToRemove);

        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> suitableExplorers = this.explorers.getCollection().stream()
                .filter(a -> a.getEnergy() > 50)
                .collect(Collectors.toList());

        if (suitableExplorers.isEmpty()){
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();
        State stateToExplore = this.states.byName(stateName);
        exploredStatesCount++;
        int suitableExplorersCount = suitableExplorers.size();
        mission.explore(stateToExplore, suitableExplorers);
        int suitableExplorersRest = suitableExplorers.size();

        return String.format(STATE_EXPLORER, stateName, suitableExplorersCount - suitableExplorersRest);

    }

    @Override
    public String finalResult() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, exploredStatesCount)).append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());
        this.explorers.getCollection().stream().forEach(e ->{
            sb.append(String.format(FINAL_EXPLORER_NAME,e.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY,e.getEnergy())).append(System.lineSeparator());

            if (e.getSuitcase().getExhibits().isEmpty()){
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None")).append(System.lineSeparator());
            } else {
                Collection<String> suitcasesExhibits = e.getSuitcase().getExhibits();
                sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, String.join(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, suitcasesExhibits))).append(System.lineSeparator());
            }
        });

        return sb.toString().trim();
    }
}
