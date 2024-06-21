package org.modelmapperexercise.data.repositories;

import org.modelmapperexercise.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findUserById(long id);
}
