package org.modelmapperexercise.service;

import org.modelmapperexercise.data.entities.User;
import org.modelmapperexercise.service.dtos.UserLoginDto;
import org.modelmapperexercise.service.dtos.UserOwnedGamesDto;
import org.modelmapperexercise.service.dtos.UserRegisterDto;

import java.util.Set;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logout();

    User getLoggedIn();

    String usersOwnedGames();
}
