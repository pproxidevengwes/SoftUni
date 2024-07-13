package com.example.jsonex.productshop.repositories;

import com.example.jsonex.productshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM users u " +
            "WHERE (SELECT COUNT (p) FROM products  p WHERE p.seller.id = u.id) > 0 " +
            "ORDER BY u.lastName,u.firstName")
    List<User> findAllUsersWithMoreThenOneSoldProductOrderByLastNameThenFirstName();


    @Query("SELECT u FROM users u " +
            "WHERE (SELECT COUNT (p) FROM  products p WHERE p.buyer.id = u.id) > 0 " +
            "ORDER BY u.products.size DESC ,u.lastName ")
    List<User> findAllUsersWithMoreThenOneSoledProducts();
}
