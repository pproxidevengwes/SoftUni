package com.example.jsonex.productshop.repositories;

import com.example.jsonex.productshop.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM categories c " +
            "ORDER BY c.products.size DESC ")
    List<Category> findAllCategoriesWithTheirCountOfProducts();
}
