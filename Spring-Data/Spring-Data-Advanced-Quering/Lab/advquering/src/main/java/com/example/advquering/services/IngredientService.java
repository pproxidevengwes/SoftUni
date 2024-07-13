package com.example.advquering.services;

import com.example.advquering.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    List<Ingredient> selectNameStartsWith(String letters);

    List<Ingredient> selectInNames(List<String> names);

    void deleteByName(String name);

    void increasePriceByPercentage();

    void increasePriceByPercentageAndName(Set<String> names);
}
