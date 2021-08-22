package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, List<Explorer> explorers) {
        List<String> exhibitsList = state.getExhibits().stream().collect(Collectors.toList());

        for (int index = 0; index < explorers.size() ; index++) {
            Explorer currentExplorer = explorers.get(index);
            while (currentExplorer.canSearch()){
                if (!exhibitsList.isEmpty()) {
                    for (int exhibit = 0; exhibit < exhibitsList.size(); exhibit++) {
                        currentExplorer.search();
                        String currentExhibit = exhibitsList.get(exhibit);
                        currentExplorer.getSuitcase().getExhibits().add(currentExhibit);
                        state.getExhibits().remove(currentExhibit);
                        exhibitsList.remove(currentExhibit);
                    }
                } else{
                    break;
                }
            }
        }
    }
}
