package com.example.gamestore.services.impl;

import com.example.gamestore.entities.game.AddGameDto;
import com.example.gamestore.entities.game.DetailViewGameDto;
import com.example.gamestore.entities.game.Game;
import com.example.gamestore.entities.game.ViewGameDto;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.GameService;
import com.example.gamestore.services.UserService;
import com.example.gamestore.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void addGame(AddGameDto addGameDto) {

        if (userNotAdmin()) {
            return;
        }
        Set<ConstraintViolation<AddGameDto>> violations = validationUtil.violations(addGameDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        
        Game game = modelMapper.map(addGameDto, Game.class);
        gameRepository.save(game);
        System.out.println("Added " + addGameDto.getTitle());
    }

    @Override
    public void editGame(Long gameId, String[] values) {

        if (userNotAdmin()) {
            return;
        }
        Game game = gameRepository.findById(gameId).orElse(null);

        if (game == null) {
            System.out.println("There is no game with the given id in the catalog");
            return;
        }
        updateGameProperties(values, game);

        AddGameDto gameEditDto = modelMapper.map(game, AddGameDto.class);

        Set<ConstraintViolation<AddGameDto>> violations = validationUtil.violations(gameEditDto);
        
        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public void deleteGame(Long gameId) {

        if (userNotAdmin()) {
            return;
        }

        Game game = gameRepository.findById(gameId).orElse(null);

        if (game == null) {
            System.out.println("There is no game with the given id in the catalog");
            return;
        }
        
        gameRepository.delete(game);
        System.out.println("Deleted " + game.getTitle());
    }

    @Override
    public void getAllGamesInfo() {
        gameRepository
                .findAll()
                .stream()
                .map(g -> modelMapper.map(g, ViewGameDto.class))
                .forEach(System.out::println);
    }

    @Override
    public void getGameInfo(String title) {
        Game game = this.gameRepository.findGameByTitle(title).orElse(null);

        if (game == null) {
            System.out.println("There is no game with the given title");
            return;
        }

        DetailViewGameDto gameDetailViewDto = modelMapper.map(game, DetailViewGameDto.class);
        System.out.println(gameDetailViewDto.toString());
    }

    private void updateGameProperties(String[] values, Game game) {
        
        for (String value : values) {
            String[] arguments = value.split("=");
            String fieldName = arguments[0];
            String fieldValue = arguments[1];
            setPropertiesToGame(fieldName, fieldValue, game);
        }
    }

    private void setPropertiesToGame(String fieldName, String fieldValue, Game game) {
        switch (fieldName) {
            case "title" -> game.setTitle(fieldValue);
            case "price" -> game.setPrice(new BigDecimal(fieldValue));
            case "size" -> game.setSize(Double.parseDouble(fieldValue));
            case "trailer" -> game.setTrailer(fieldValue);
            case "thumbnailURL" -> game.setThumbnailUrl(fieldValue);
            case "description" -> game.setDescription(fieldValue);
            case "releaseDate" -> game.setReleaseDate(LocalDate.parse(fieldValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }

    private boolean userNotAdmin() {
        if (!userService.hasLoggedInUser()) {
            System.out.println("User not logged in");
            return true;
        }

        if (userService.hasLoggedInUser() && !userService.isUserAdmin()) {
            System.out.println("Logged in user is not an Admin");
            return true;
        }
        return false;
    }
}
