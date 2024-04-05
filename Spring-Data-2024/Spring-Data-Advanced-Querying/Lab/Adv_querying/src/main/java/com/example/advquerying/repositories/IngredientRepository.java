package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Set<Ingredient> findAllByNameStartsWith(String symbol);

    Set<Ingredient> findAllByNameInOrderByPriceAsc(List<String> names);

    Set<Ingredient> findAllByPriceLessThan(BigDecimal price);

    @Transactional
    @Modifying
    @Query("DELETE Ingredient WHERE name=:name")
    int deleteIngredientByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percent")
    int updateAllByPrice(BigDecimal percent);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percent WHERE name IN :names")
    int updateAllByPriceForGivenNames(BigDecimal percent, @Param("names")List<String> strings);
}
