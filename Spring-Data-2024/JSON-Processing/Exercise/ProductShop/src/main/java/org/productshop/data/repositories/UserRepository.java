package org.productshop.data.repositories;

import org.productshop.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Set<User>findAllBySoldBuyerIsNotNull();



}
