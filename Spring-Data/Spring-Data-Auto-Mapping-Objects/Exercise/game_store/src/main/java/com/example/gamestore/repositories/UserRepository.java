package com.example.gamestore.repositories;

import com.example.gamestore.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPasswordAndEmail(String password, String email);
}
