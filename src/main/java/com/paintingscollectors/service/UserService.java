package com.paintingscollectors.service;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession userSession, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }


    public boolean register(UserRegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return false; // Passwords don't match
        }
        boolean isUsernameOrEmailTaken = this.userRepository.existsByUsernameOrEmail(registerDTO.getUsername(), registerDTO.getEmail());
        if (isUsernameOrEmailTaken) {
            return false; // Username or Email already exists// Username or Email already exists
        }


        User newUser = this.modelMapper.map(registerDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        this.userRepository.save(newUser);
        return true;
    }


    public boolean login(UserLoginDTO loginDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsername(loginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }
        boolean passwordsMatch = this.passwordEncoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword());

        if (!passwordsMatch) {
            return false;

        }
        this.userSession.login(optionalUser.get().getId(), loginDTO.getUsername());
        return true;
    }

    public Set<PaintingDisplayDTO> getAllFavoritePaintings(long id) {
        return userRepository.findById(id)
                .map(User::getFavouritePaintings)
                .orElseGet(HashSet::new)
                .stream()
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toSet());
    }

}

