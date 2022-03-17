package com.example.advquering.services;

import com.example.advquering.entities.Shampoo;
import com.example.advquering.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> selectBySize(Size size);

    List<Shampoo> selectBySizeOrLabelId(Size size, Long id);

    List<Shampoo> selectMoreExpensiveThan(BigDecimal price);

    int countPriceLowerThan(BigDecimal price);

    List<Shampoo> selectByIngredientsCount(Set<String> names);

    List<Shampoo> selectByIngredients(int count);
}
