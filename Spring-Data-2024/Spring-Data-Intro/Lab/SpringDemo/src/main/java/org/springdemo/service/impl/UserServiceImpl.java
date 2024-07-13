package org.springdemo.service.impl;

import org.springdemo.data.entities.User;
import org.springdemo.data.repositories.UserRepository;
import org.springdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(int id) {
        Optional<User> optional = this.userRepository.findById(id);
        if ((optional.isPresent())) {
            return optional.get();
        }
        System.out.println("No such user with given id - " + id);
        return null;
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByUsername(name);
    }

    @Override
    public List<User> findByPrefix(String prefix) {
        return this.userRepository.findAllByUsernameContaining(prefix);
    }


}
