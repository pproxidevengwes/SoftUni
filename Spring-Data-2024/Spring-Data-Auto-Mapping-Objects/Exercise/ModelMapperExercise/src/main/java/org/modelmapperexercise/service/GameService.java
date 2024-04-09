package org.modelmapperexercise.service;

import org.modelmapperexercise.service.dtos.GameAddDto;
import org.modelmapperexercise.service.dtos.GamesAllDto;

import java.util.Map;
import java.util.Set;

public interface GameService {
    String addGame(GameAddDto gameAddDto);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    Set<GamesAllDto> getAllGames();

    String allGamesReadyForPrint();

    String detailGame(String[] tokens);
}
