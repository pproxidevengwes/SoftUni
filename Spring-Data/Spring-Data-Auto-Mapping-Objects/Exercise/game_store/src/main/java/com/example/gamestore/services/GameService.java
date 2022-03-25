package com.example.gamestore.services;

import com.example.gamestore.entities.user.LoginDto;
import com.example.gamestore.entities.user.RegisterDto;

public interface UserService {
    void registerUser(RegisterDto userRegisterDto);

    void loginUser(LoginDto userLoginDto);

    void logout();

    boolean hasLoggedInUser();

    boolean isUserAdmin();

    void getCurrentUserGames();
}
