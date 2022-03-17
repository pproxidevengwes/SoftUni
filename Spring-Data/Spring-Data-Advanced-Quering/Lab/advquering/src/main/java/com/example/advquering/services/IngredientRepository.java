package com.example.advquering.services;

import com.example.advquering.entities.Ingredient;
import com.example.advquering.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> selectNameStartsWith(String letters) {
        return this.ingredientRepository.findByNameStartingWith(letters);
    }

    @Override
    public List<Ingredient> selectInNames(List<String> names) {
        return this.ingredientRepository.findByNameInOrderByPriceAsc(names);
    }

    @Override
    public void deleteByName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    public void increasePriceByPercentage() {
        this.ingredientRepository.updateAllIngredientsPrice();
    }

    @Override
    public void increasePriceByPercentageAndName(Set<String> names) {
        this.ingredientRepository.updatePriceByName(names);
    }
}
