package com.bonappetit.service;

import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession usersession;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession usersession, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersession = usersession;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDTO data) {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        this.userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO data) {
        Optional<User> byUsername = userRepository.findByUsername(data.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }

        boolean matches = passwordEncoder.matches(data.getPassword(), byUsername.get().getPassword());

        if (!matches) {
            return false;
        }
        userSession.login(byUsername.get().getId(), byUsername.get().getUsername());
        return true;
    }

    @Transactional
    public List<Recipe> findFavourites(Long userId) {
        Optional<User> byId = userRepository.findById(userId);

        if (byId.isEmpty()) {
            return new ArrayList<>();

        }

        return byId.get().getFavouriteRecipes();
    }
}
