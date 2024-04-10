package org.modelmapperexercise.service.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.modelmapperexercise.data.entities.Game;
import org.modelmapperexercise.data.entities.User;
import org.modelmapperexercise.data.repositories.UserRepository;
import org.modelmapperexercise.service.UserService;
import org.modelmapperexercise.service.dtos.UserLoginDto;
import org.modelmapperexercise.service.dtos.UserOwnedGamesDto;
import org.modelmapperexercise.service.dtos.UserRegisterDto;
import org.modelmapperexercise.util.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private User loggedIn;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ValidatorService validatorService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.validatorService = validatorService;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        if (!this.validatorService.isValid(userRegisterDto)) {
            Set<ConstraintViolation<UserRegisterDto>> set = this.validatorService.validate(userRegisterDto);
            return set.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        }

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "Passwords do not match.";
        }

        Optional<User> optional = this.userRepository.findUserByEmail(userRegisterDto.getEmail());

        if (optional.isPresent()) {
            return "User with this email already exist.";
        }

        User user = this.modelMapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        this.userRepository.saveAndFlush(user);

        return String.format("%s was registered.", user.getFullName());
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        Optional<User> optional = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
        if (optional.isEmpty()) {
            return "Incorrect username / password.";
        }
        setLoggedIn(optional.get());

        return String.format("Successfully logged in %s.", optional.get().getFullName());
    }

    @Override
    public String logout() {
        if (this.loggedIn == null) {
            return "Cannot log out. No user was logged in.";
        }
        String output = String.format("User %s successfully logged out.", this.loggedIn.getFullName());
        setLoggedIn(null);
        return output;
    }

    @Override
    public User getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(User loggedIn) {
        this.loggedIn = loggedIn;
    }

    private Set<Game> games = new HashSet<>();

    @Override
    public String usersOwnedGames() {
        if (this.loggedIn == null) {
            return "No user is logged in.";
        }
        User user = this.userRepository.findUserById(this.loggedIn.getId()).get();
        Set<Game> ownedGames = user.getGames();
        if (ownedGames.isEmpty()) {
            return String.format("%s does not own any games.", user.getFullName());
        }
        Set<UserOwnedGamesDto> userOwnedGames = ownedGames
                .stream()
                .map(g -> modelMapper.map(g, UserOwnedGamesDto.class))
                .collect(Collectors.toSet());

       List<String> output=new ArrayList<>();
        for (UserOwnedGamesDto userOwnedGame : userOwnedGames) {
            output.add(userOwnedGame.getTitle());
        }
        System.out.println(user.getFullName() + " owns the following games:");
        return String.join("\n", output);
    }
}
