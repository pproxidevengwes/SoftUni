package org.springdemo.data.repositories;

import org.springdemo.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findAllByUsernameContaining(String prefix);
}
