package com.example.advquerying.service;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<String> getAllIngredientsWithStartingName(String symbol);

    List<String> getAllIngredientsNamesOrderByPriceAsc(List<String> names);

    int countOfIngredientsWithPriceLesserThan(BigDecimal price);

    int deleteIngredientByName(String name);

    int updateIngredientPrices();

    int updateIngredientPricesForGivenNames();

}
