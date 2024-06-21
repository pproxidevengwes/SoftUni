package org.modelmapperexercise.service.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.modelmapperexercise.data.entities.Game;
import org.modelmapperexercise.data.repositories.GameRepository;
import org.modelmapperexercise.service.GameService;
import org.modelmapperexercise.service.UserService;
import org.modelmapperexercise.service.dtos.GameAddDto;
import org.modelmapperexercise.service.dtos.GameDetailsDto;
import org.modelmapperexercise.service.dtos.GamesAllDto;
import org.modelmapperexercise.util.ValidatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private ValidatorService validatorService;
    private UserService userService;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ValidatorService validatorService, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.validatorService = validatorService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public String addGame(GameAddDto gameAddDto) {
        if (this.userService.getLoggedIn() != null && this.userService.getLoggedIn().isAdmin()) {
            if (!this.validatorService.isValid(gameAddDto)) {
                return this.validatorService.validate(gameAddDto)
                        .stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("\n"));
            }
            Game game = this.modelMapper.map(gameAddDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            return String.format("Added %s.", game.getTitle());
        }
        return "Logged in user is not an admin.";
    }

    @Override
    public String editGame(long id, Map<String, String> map) {
        Optional<Game> optional = this.gameRepository.findById(id);
        if (optional.isEmpty()) {
            return "No such game with given id.";
        }
        Game game = optional.get();
        String output = String.format("Edited %s.", game.getTitle());

        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "title":
                    game.setTitle(entry.getValue());
                    break;
                case "price":
                    game.setPrice(Double.parseDouble(entry.getValue()));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(entry.getValue()));
                    break;
                case "trailer":
                    game.setTrailer(entry.getValue());
                    break;
                case "thumbnail":
                    game.setThumbnail(entry.getValue());
                    break;
                case "description":
                    game.setDescription(entry.getValue());
                    break;
                case "releaseDate":
                    game.setReleaseDate(LocalDate.parse(entry.getValue(), DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
                    break;
            }
        }

        this.gameRepository.saveAndFlush(game);
        return output;
    }

    @Override
    public String deleteGame(long id) {

        Optional<Game> optional = this.gameRepository.findById(id);
        if (optional.isEmpty()) {
            return "No such game with given id.";
        }
        String output = String.format("Deleted %s.", optional.get().getTitle());
        this.gameRepository.delete(optional.get());
        return output;
    }

    @Override
    public Set<GamesAllDto> getAllGames() {
        return this.gameRepository.findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, GamesAllDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String allGamesReadyForPrint() {
        return this.getAllGames()
                .stream()
                .map(GamesAllDto::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String detailGame(String[] tokens) {
        String title = tokens[1];

        Optional<Game> firstByTitle = this.gameRepository.findByTitle(title);

        if (firstByTitle.isEmpty()) {
            return String.format("Game with title %s does not exist.", title);
        }

        Game game = firstByTitle.get();

        GameDetailsDto map = modelMapper.map(game, GameDetailsDto.class);

        return map.toString();
    }
}
