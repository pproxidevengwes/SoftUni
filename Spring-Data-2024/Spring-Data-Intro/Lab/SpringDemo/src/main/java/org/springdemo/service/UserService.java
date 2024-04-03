package org.springdemo.service;

import org.springdemo.data.entities.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User findUserById(int id);

    User findByName(String name);

    List<User> findByPrefix(String name);

}
